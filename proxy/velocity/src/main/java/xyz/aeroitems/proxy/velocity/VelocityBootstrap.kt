package xyz.aeroitems.proxy.velocity

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger
import xyz.aeroitems.proxy.velocity.network.RedisManager
import xyz.aeroitems.proxy.velocity.network.VelocityNetworkService

@Plugin(
    id = "aeroitems-proxy",
    name = "AeroItems Proxy",
    version = "1.0.0"
)
class VelocityBootstrap @Inject constructor(
    private val logger: Logger
) {

    lateinit var network: VelocityNetworkService

    @Subscribe
    fun onProxyInit(event: ProxyInitializeEvent) {
        logger.info("AeroItems Velocity başlatılıyor...")

        val redis = RedisManager("redis://localhost:6379")

        network = VelocityNetworkService(redis)
        network.init()

        logger.info("AeroItems Velocity network aktif.")
    }
}
