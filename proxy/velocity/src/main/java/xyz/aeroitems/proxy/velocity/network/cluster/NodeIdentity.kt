package xyz.aeroitems.proxy.velocity.network.cluster

import xyz.aeroitems.proxy.velocity.network.packet.impl.NodeHeartbeatPacket
import java.net.InetAddress
import java.util.UUID

data class NodeIdentity(
    val id: String = UUID.randomUUID().toString(),
    val type: NodeType,
    val host: String = InetAddress.getLocalHost().hostAddress,
    val startTime: Long = System.currentTimeMillis()
) {
    fun toPacket() = NodeHeartbeatPacket(this)
}
