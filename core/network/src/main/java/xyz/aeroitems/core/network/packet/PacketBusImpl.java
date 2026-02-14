package xyz.aeroitems.core.network.packet;

import xyz.aeroitems.core.network.redis.StreamBus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PacketBusImpl implements PacketBus {

    private final StreamBus streamBus;
    private final PacketSerializer serializer;

    private final Map<Class<?>, PacketHandler<?>> handlers = new ConcurrentHashMap<>();

    public PacketBusImpl(StreamBus streamBus, PacketSerializer serializer) {
        this.streamBus = streamBus;
        this.serializer = serializer;

        streamBus.subscribe("astral:packetbus", (stream, payload) -> {
            Packet packet = serializer.deserialize(payload);
            dispatch(packet);
        });
    }

    @Override
    public void publish(Packet packet) {
        streamBus.publish("astral:packetbus", serializer.serialize(packet));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Packet> void subscribe(Class<T> type, PacketHandler<T> handler) {
        handlers.put(type, handler);
    }

    @SuppressWarnings("unchecked")
    private void dispatch(Packet packet) {
        PacketHandler handler = handlers.get(packet.getClass());
        if (handler != null) handler.handle(packet);
    }
}
