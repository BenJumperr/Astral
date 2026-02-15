package xyz.aeroitems.core.player.state

import java.util.UUID

data class PlayerState(
    val uuid: UUID,
    var server: String,
    var world: String,
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float,
    var pitch: Float,
    var velocityX: Double,
    var velocityY: Double,
    var velocityZ: Double,
    var health: Double,
    var ping: Int,
    var lastUpdate: Long
)
