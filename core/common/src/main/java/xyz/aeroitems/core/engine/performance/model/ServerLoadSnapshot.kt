package xyz.aeroitems.core.engine.performance.model

data class ServerLoadSnapshot(
    val tps: Double,
    val mspt: Double,
    val cpuLoad: Double,
    val memoryUsage: Double,
    val onlinePlayers: Int,
    val loadedChunks: Int,
    val entityCount: Int,
    val timestamp: Long = System.currentTimeMillis(),
)
