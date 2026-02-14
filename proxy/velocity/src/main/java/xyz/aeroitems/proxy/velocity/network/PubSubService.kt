package xyz.aeroitems.proxy.velocity.network

class PubSubService(
    private val redis: RedisManager,
    private val channel: String
) {

    fun subscribe(listener: (String) -> Unit) {
        redis.addListener(listener)
        redis.subscribe(channel)
    }

    fun publish(message: String) {
        redis.publish(channel, message)
    }
}
