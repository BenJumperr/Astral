package xyz.aeroitems.core.engine.chunk

data class ChunkTicket(
    val world: String,
    val x: Int,
    val z: Int,
    val priority: Int
)
