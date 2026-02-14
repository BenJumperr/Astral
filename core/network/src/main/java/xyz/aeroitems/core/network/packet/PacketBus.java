package xyz.aeroitems.core.network.packet;

public interface PacketBus {
    void publish(Packet packet);
    <T extends Packet> void subscribe(Class<T> type, PacketHandler<T> handler);
}
