package xyz.aeroitems.proxy.velocity.network.routing

data class NodeLoad(
    val cpu: Double,
    val memory: Double,
    val players: Int
) {
    fun score(): Double {
        return cpu * 0.5 + memory * 0.3 + players * 0.2
    }
}
