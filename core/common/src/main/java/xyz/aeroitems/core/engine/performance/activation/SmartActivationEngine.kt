package xyz.aeroitems.core.engine.performance.activation

import xyz.aeroitems.core.engine.performance.model.EntityActivationDecision
import xyz.aeroitems.core.engine.performance.tick.DynamicTickEngine
import kotlin.math.max

class SmartActivationEngine(
    private val tickEngine: DynamicTickEngine
) {

    fun evaluate(
        distance: Double,
        isVisible: Boolean,
        inCombat: Boolean,
        importance: Int
    ): EntityActivationDecision {

        var priority = importance

        priority += when {
            inCombat -> 40
            isVisible -> 20
            else -> 0
        }

        priority += (max(0.0, 64 - distance) * 0.5).toInt()

        priority = priority.coerceIn(0, 100)

        val tickRate = tickEngine.calculateTickRate(priority)

        return EntityActivationDecision(
            shouldTick = priority > 10,
            tickRate = tickRate,
            priority = priority
        )
    }
}
