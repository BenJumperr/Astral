package xyz.aeroitems.core.engine.teleport

data class TeleportContext(
    val playerId: String,
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
)

interface ZeroLagTeleportEngine {
    fun prepare(ctx: TeleportContext)
    fun commit(ctx: TeleportContext)
}
