package xyz.aeroitems.proxy.velocity.network.packet.impl

import xyz.aeroitems.proxy.velocity.network.cluster.NodeIdentity
import xyz.aeroitems.proxy.velocity.network.packet.Packet

data class NodeHeartbeatPacket( val identity: NodeIdentity ) : Packet
