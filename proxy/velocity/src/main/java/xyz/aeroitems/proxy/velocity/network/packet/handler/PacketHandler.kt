package xyz.aeroitems.proxy.velocity.network.packet.handler

import xyz.aeroitems.proxy.velocity.network.packet.Packet
import java.util.concurrent.ConcurrentHashMap

object PacketHandler {

    private val handlers = ConcurrentHashMap<Class<out Packet>, (Packet) -> Unit>()

    fun <T : Packet> register(
        clazz: Class<T>,
        handler: (T) -> Unit
    ) {
        handlers[clazz] = { handler(clazz.cast(it)) }
    }

    fun handle(packet: Packet) {
        handlers[packet.javaClass]?.invoke(packet)
    }
}