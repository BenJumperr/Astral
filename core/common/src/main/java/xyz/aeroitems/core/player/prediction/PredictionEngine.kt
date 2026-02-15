package xyz.aeroitems.core.player.prediction

import xyz.aeroitems.core.player.state.PlayerState

object PredictionEngine {

    fun predictNext(state: PlayerState, deltaTime: Long): PlayerState {
        val context = PredictionContext(state, deltaTime)
        return MovementPredictor.predict(context)
    }
}