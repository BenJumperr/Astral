package xyz.aeroitems.core.player.prediction

import xyz.aeroitems.core.player.state.PlayerState

object MovementPredictor {

    fun predict(context: PredictionContext): PlayerState {
        val s = context.previous
        val dt = context.deltaTime / 1000.0

        val predictedX = s.x + s.velocityX * dt
        val predictedY = s.y + s.velocityY * dt
        val predictedZ = s.z + s.velocityZ * dt

        return s.copy(
            x = predictedX,
            y = predictedY,
            z = predictedZ,
            lastUpdate = System.currentTimeMillis()
        )
    }
}