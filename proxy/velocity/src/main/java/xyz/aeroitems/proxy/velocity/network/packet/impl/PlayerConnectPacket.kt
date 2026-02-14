package xyz.aeroitems.proxy.velocity.network.packet.impl

import xyz.aeroitems.proxy.velocity.network.packet.Packet
import java.util.UUID

data class PlayerConnectPacket(
    val uuid: UUID,
    val username: String,
    val server: String
) : Packet
