package xyz.aeroitems.proxy.velocity.network

import io.lettuce.core.RedisClient
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands
import io.lettuce.core.pubsub.RedisPubSubAdapter

class RedisManager(uri: String) {

    private val client: RedisClient = RedisClient.create(uri)

    private val pubSubConnection: StatefulRedisPubSubConnection<String, String> =
        client.connectPubSub()

    private val pubSubCommands: RedisPubSubCommands<String, String> =
        pubSubConnection.sync()

    fun addListener(handler: (String) -> Unit) {
        pubSubConnection.addListener(object : RedisPubSubAdapter<String, String>() {
            override fun message(channel: String, message: String) {
                handler(message)
            }
        })
    }

    fun subscribe(channel: String) {
        pubSubCommands.subscribe(channel)
    }

    fun publish(channel: String, message: String) {
        client.connect().use {
            it.sync().publish(channel, message)
        }
    }

    fun shutdown() {
        pubSubConnection.close()
        client.shutdown()
    }
}
