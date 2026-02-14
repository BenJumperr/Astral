package xyz.aeroitems.proxy.velocity.network.packet.impl

import xyz.aeroitems.proxy.velocity.network.packet.Packet
import xyz.aeroitems.proxy.velocity.network.routing.NodeLoad

data class NodeLoadPacket(
    val nodeId: String,
    val load: NodeLoad
) : Packet
