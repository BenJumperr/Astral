package xyz.aeroitems.core.player.state

import java.util.UUID

class PlayerStateCache(
    private val redis: RedisStateStorage
) {

    fun get(uuid: UUID): PlayerState? {
        return LocalStateCache.get(uuid) ?: redis.load(uuid)?.also {
            LocalStateCache.update(it)
        }
    }

    fun update(state: PlayerState) {
        LocalStateCache.update(state)
        redis.save(state)
    }
}