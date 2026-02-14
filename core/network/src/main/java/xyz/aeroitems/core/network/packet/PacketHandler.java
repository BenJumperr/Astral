package xyz.aeroitems.core.network.packet;

public interface PacketHandler<T extends Packet> {
    void handle(T packet);
}
