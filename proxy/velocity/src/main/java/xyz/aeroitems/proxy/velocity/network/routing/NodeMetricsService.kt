package xyz.aeroitems.proxy.velocity.network.routing

import com.sun.management.OperatingSystemMXBean
import java.lang.management.ManagementFactory

object NodeMetricsService {

    private val osBean =
        ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean

    fun currentLoad(players: Int): NodeLoad {
        return NodeLoad(
            cpu = osBean.systemCpuLoad.coerceAtLeast(0.01),
            memory = 1.0 - (osBean.freeMemorySize.toDouble() / osBean.totalMemorySize),
            players = players
        )
    }
}