package xyz.aeroitems.proxy.velocity.network

import com.google.gson.Gson
import xyz.aeroitems.proxy.velocity.network.cluster.ClusterService
import xyz.aeroitems.proxy.velocity.network.cluster.HeartbeatService
import xyz.aeroitems.proxy.velocity.network.cluster.NodeIdentity
import xyz.aeroitems.proxy.velocity.network.cluster.NodeType
import xyz.aeroitems.proxy.velocity.network.codec.PacketCodec
import xyz.aeroitems.proxy.velocity.network.packet.Packet
import xyz.aeroitems.proxy.velocity.network.packet.PacketRegistry
import xyz.aeroitems.proxy.velocity.network.packet.RoutedPacket
import xyz.aeroitems.proxy.velocity.network.packet.handler.PacketHandler

class VelocityNetworkService(
    redis: RedisManager
) {

    private val gson: Gson = PacketCodec.gson()
    private val pubSub = PubSubService(redis, "aeroitems:network")

    private lateinit var clusterService: ClusterService
    private lateinit var heartbeatService: HeartbeatService

    fun init() {
        PacketRegistry.registerDefaults()

        clusterService = ClusterService()
        clusterService.init()

        val identity = NodeIdentity(type = NodeType.PROXY)
        heartbeatService = HeartbeatService(identity, this)
        heartbeatService.start()

        pubSub.subscribe { json ->
            val packet = gson.fromJson(json, Packet::class.java)
            PacketHandler.handle(packet)
        }
    }

    fun send(packet: Packet) {
        pubSub.publish(gson.toJson(packet, Packet::class.java))
    }

    fun route(packet: RoutedPacket) {
        packet.targets.forEach { send(packet) }
    }
}
