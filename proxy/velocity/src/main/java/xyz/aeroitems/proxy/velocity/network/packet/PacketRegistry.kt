package xyz.aeroitems.proxy.velocity.network.packet

import xyz.aeroitems.proxy.velocity.network.packet.impl.NodeHeartbeatPacket
import xyz.aeroitems.proxy.velocity.network.packet.impl.NodeLoadPacket
import xyz.aeroitems.proxy.velocity.network.packet.impl.PlayerConnectPacket
import java.util.concurrent.ConcurrentHashMap

object PacketRegistry {

    private val idToClass = ConcurrentHashMap<String, Class<out Packet>>()
    private val classToId = ConcurrentHashMap<Class<out Packet>, String>()

    fun register(id: String, clazz: Class<out Packet>) {
        idToClass[id] = clazz
        classToId[clazz] = id
    }

    fun idOf(clazz: Class<out Packet>): String =
        classToId[clazz] ?: error("Packet not registered: ${clazz.name}")

    fun classOf(id: String): Class<out Packet>? =
        idToClass[id]

    fun registerDefaults() {
        register("player_connect", PlayerConnectPacket::class.java)
        register("node_heartbeat", NodeHeartbeatPacket::class.java)
        register("node_load", NodeLoadPacket::class.java)
    }
}
