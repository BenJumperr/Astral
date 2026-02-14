package xyz.aeroitems.core.network.redis;

import io.lettuce.core.XReadArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public final class StreamBus {

    private final RedisAsyncCommands<String, byte[]> redis;
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final Map<String, StreamListener> listeners = new ConcurrentHashMap<>();

    public StreamBus(RedisAsyncCommands<String, byte[]> redis) {
        this.redis = redis;
    }

    public void subscribe(String stream, StreamListener listener) {
        listeners.put(stream, listener);
        startConsumer(stream);
    }

    public void publish(String stream, byte[] payload) {
        redis.xadd(stream, Map.of("data", payload));
    }

    private void startConsumer(String stream) {
        executor.submit(() -> {
            String lastId = "0";
            while (true) {
                var result = redis.xread(
                        XReadArgs.Builder.block(Duration.ofSeconds(5)),
                        XReadArgs.StreamOffset.from(stream, lastId)
                ).get();

                if (result == null) continue;

                for (var msg : result) {
                    for (var entry : msg.getBody().values()) {
                        listeners.get(stream).onMessage(stream, entry);
                    }
                    lastId = msg.getId();
                }
            }
        });
    }
}
