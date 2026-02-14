package xyz.aeroitems.core.network.packet;

public interface PacketSerializer {
    byte[] serialize(Packet packet);
    Packet deserialize(byte[] data);
}
