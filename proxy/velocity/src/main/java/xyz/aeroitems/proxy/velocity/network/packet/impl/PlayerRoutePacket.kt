package xyz.aeroitems.proxy.velocity.network.packet.impl

import xyz.aeroitems.proxy.velocity.network.packet.RoutedPacket
import java.util.UUID

data class PlayerRoutePacket(
    val uuid: UUID,
    val server: String,
    override val targets: List<String>
) : RoutedPacket
