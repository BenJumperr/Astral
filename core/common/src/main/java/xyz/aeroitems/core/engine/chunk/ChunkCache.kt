package xyz.aeroitems.core.engine.chunk

import java.util.concurrent.ConcurrentHashMap

object ChunkCache {

    private val loaded = ConcurrentHashMap<String, Long>()

    fun key(world: String, x: Int, z: Int) = "$world:$x:$z"

    fun markLoaded(world: String, x: Int, z: Int) {
        loaded[key(world, x, z)] = System.currentTimeMillis()
    }

    fun isLoaded(world: String, x: Int, z: Int): Boolean {
        return loaded.containsKey(key(world, x, z))
    }
}