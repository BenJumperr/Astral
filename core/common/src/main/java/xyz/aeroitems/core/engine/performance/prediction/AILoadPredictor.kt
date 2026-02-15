package xyz.aeroitems.core.engine.performance.prediction

import xyz.aeroitems.core.engine.performance.model.ServerLoadSnapshot
import java.util.*
import kotlin.math.max

class AILoadPredictor {

    private val history = LinkedList<ServerLoadSnapshot>()
    private val maxHistory = 120

    fun record(snapshot: ServerLoadSnapshot) {
        history += snapshot
        if (history.size > maxHistory) history.poll()
    }

    fun predictNext(secondsAhead: Int = 5): ServerLoadSnapshot? {
        if (history.size < 10) return null

        val avgTps = history.map { it.tps }.average()
        val avgMspt = history.map { it.mspt }.average()
        val avgCpu = history.map { it.cpuLoad }.average()

        val cpuTrend = history.last().cpuLoad - history.first().cpuLoad

        val predictedCpu = max(0.0, avgCpu + cpuTrend * (secondsAhead / 5.0))

        return ServerLoadSnapshot(
            tps = avgTps - (predictedCpu * 0.05),
            mspt = avgMspt + (predictedCpu * 1.5),
            cpuLoad = predictedCpu,
            memoryUsage = history.last().memoryUsage,
            onlinePlayers = history.last().onlinePlayers,
            loadedChunks = history.last().loadedChunks,
            entityCount = history.last().entityCount
        )
    }
}
