package xyz.aeroitems.core.player.prediction

import xyz.aeroitems.core.player.state.PlayerState

object CombatPredictor {

    fun willHit(attacker: PlayerState, target: PlayerState): Boolean {
        val dx = attacker.x - target.x
        val dz = attacker.z - target.z
        return (dx * dx + dz * dz) <= 16
    }
}