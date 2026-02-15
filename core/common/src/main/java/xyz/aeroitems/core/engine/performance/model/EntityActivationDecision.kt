package xyz.aeroitems.core.engine.performance.model

data class EntityActivationDecision(
    val shouldTick: Boolean,
    val tickRate: Int,
    val priority: Int
)
