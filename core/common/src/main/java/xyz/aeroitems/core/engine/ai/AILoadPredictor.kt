package xyz.aeroitems.core.engine.ai

import java.util.concurrent.ConcurrentHashMap
import kotlin.math.roundToInt

class AILoadPredictor {

    private val serverLoadHistory = ConcurrentHashMap<String, MutableList<Double>>()

    fun record(serverId: String, load: Double) {
        serverLoadHistory
            .computeIfAbsent(serverId) { mutableListOf() }
            .apply {
                add(load)
                if (size > 120) removeAt(0)
            }
    }

    fun predict(serverId: String): Double {
        val history = serverLoadHistory[serverId] ?: return 0.0
        if (history.isEmpty()) return 0.0

        val trend = history.takeLast(20)
        return trend.average()
    }

    fun recommendedCapacity(serverId: String): Int {
        return (predict(serverId) * 100).roundToInt()
    }
}
