package xyz.aeroitems.proxy.velocity.network.cluster

import xyz.aeroitems.proxy.velocity.network.VelocityNetworkService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class HeartbeatService(
    private val identity: NodeIdentity,
    private val network: VelocityNetworkService
) {

    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    fun start() {
        scheduler.scheduleAtFixedRate({
            network.send(identity.toPacket())
        }, 0, 2, TimeUnit.SECONDS)
    }

    fun shutdown() {
        scheduler.shutdownNow()
    }
}
