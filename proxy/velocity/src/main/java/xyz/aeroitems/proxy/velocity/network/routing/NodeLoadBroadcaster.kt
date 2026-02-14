package xyz.aeroitems.proxy.velocity.network.routing

import xyz.aeroitems.proxy.velocity.network.VelocityNetworkService
import xyz.aeroitems.proxy.velocity.network.packet.impl.NodeLoadPacket
import java.util.concurrent.*

class NodeLoadBroadcaster(
    private val nodeId: String,
    private val network: VelocityNetworkService
) {

    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    fun start(playerProvider: () -> Int) {
        scheduler.scheduleAtFixedRate({
            val load = NodeMetricsService.currentLoad(playerProvider())
            network.send(NodeLoadPacket(nodeId, load))
        }, 2, 2, TimeUnit.SECONDS)
    }
}