package xyz.aeroitems.core.player.state

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.lettuce.core.api.sync.RedisCommands
import java.util.UUID

class RedisStateStorage(
    private val redis: RedisCommands<String, String>
) {

    private val mapper = jacksonObjectMapper()

    fun save(state: PlayerState) {
        redis.set("player:${state.uuid}", mapper.writeValueAsString(state))
    }

    fun load(uuid: UUID): PlayerState? {
        return redis.get("player:$uuid")?.let {
            mapper.readValue(it, PlayerState::class.java)
        }
    }
}