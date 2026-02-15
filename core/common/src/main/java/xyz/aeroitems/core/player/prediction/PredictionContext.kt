package xyz.aeroitems.core.player.prediction

import xyz.aeroitems.core.player.state.PlayerState

data class PredictionContext(
    val previous: PlayerState,
    val deltaTime: Long
)
