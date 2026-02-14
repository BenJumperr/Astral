package xyz.aeroitems.proxy.velocity.network.packet

interface RoutedPacket : Packet {
    val targets: List<String>
}