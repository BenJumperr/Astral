package xyz.aeroitems.proxy.velocity.network.cluster

import xyz.aeroitems.proxy.velocity.network.packet.handler.PacketHandler
import xyz.aeroitems.proxy.velocity.network.packet.impl.NodeHeartbeatPacket
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ClusterService {

    private val cleanupScheduler = Executors.newSingleThreadScheduledExecutor()

    fun init() {
        PacketHandler.register(NodeHeartbeatPacket::class.java) {
            NodeRegistry.update(it.identity)
        }

        cleanupScheduler.scheduleAtFixedRate({
            cleanup()
        }, 5, 5, TimeUnit.SECONDS)
    }

    private fun cleanup() {
        val now = System.currentTimeMillis()

        NodeRegistry.getAll().forEach {
            if (now - it.lastHeartbeat > 8000) {
                NodeRegistry.remove(it.identity.id)
            }
        }
    }
}
