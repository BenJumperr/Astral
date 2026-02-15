package xyz.aeroitems.core.engine.chunk

import java.util.concurrent.ConcurrentLinkedQueue

class ChunkPreloadEngine {

    private val preloadQueue = ConcurrentLinkedQueue<ChunkTask>()

    fun schedule(world: String, x: Int, z: Int, priority: Int = 0) {
        preloadQueue.add(ChunkTask(world, x, z, priority))
    }

    fun poll(): ChunkTask? = preloadQueue.poll()

    data class ChunkTask(
        val world: String,
        val x: Int,
        val z: Int,
        val priority: Int
    )
}
