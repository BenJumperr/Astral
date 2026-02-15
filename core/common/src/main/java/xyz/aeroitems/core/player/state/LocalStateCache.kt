package xyz.aeroitems.core.player.state

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object LocalStateCache {

    private val cache = ConcurrentHashMap<UUID, PlayerState>()

    fun get(uuid: UUID): PlayerState? = cache[uuid]

    fun update(state: PlayerState) {
        cache[state.uuid] = state
    }

    fun remove(uuid: UUID) {
        cache.remove(uuid)
    }
}