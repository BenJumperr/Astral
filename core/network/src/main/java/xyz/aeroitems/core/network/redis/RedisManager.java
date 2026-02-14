package xyz.aeroitems.core.network.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;

public final class RedisManager {

    private final RedisClient client;
    private final StatefulRedisConnection<String, byte[]> connection;

    public RedisManager(String uri) {
        this.client = RedisClient.create(uri);
        this.connection = client.connect(
                RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE)
        );
    }

    public RedisAsyncCommands<String, byte[]> async() {
        return connection.async();
    }

    public void shutdown() {
        connection.close();
        client.shutdown();
    }
}
