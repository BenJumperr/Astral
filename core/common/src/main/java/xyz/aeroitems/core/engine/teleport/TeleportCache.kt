package xyz.aeroitems.core.engine.teleport

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object TeleportCache {

    private val cache = ConcurrentHashMap<UUID, Long>()

    fun warmup(uuid: UUID) {
        cache[uuid] = System.currentTimeMillis()
    }

    fun isWarm(uuid: UUID): Boolean {
        return cache.containsKey(uuid)
    }

    fun clear(uuid: UUID) {
        cache.remove(uuid)
    }
}