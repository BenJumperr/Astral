package xyz.aeroitems.core.network;

import xyz.aeroitems.core.network.packet.PacketBus;
import xyz.aeroitems.core.network.redis.RedisManager;

public interface AstralNetwork {

    RedisManager redis();
    PacketBus packetBus();
}
