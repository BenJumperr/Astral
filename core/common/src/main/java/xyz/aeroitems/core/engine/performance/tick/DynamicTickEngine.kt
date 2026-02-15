package xyz.aeroitems.core.engine.performance.tick

import xyz.aeroitems.core.engine.performance.model.ServerLoadSnapshot
import java.util.concurrent.atomic.AtomicInteger

class DynamicTickEngine {

    private val tickBudget = AtomicInteger(50)

    fun update(snapshot: ServerLoadSnapshot) {
        when {
            snapshot.tps >= 19.5 -> tickBudget.set(50)
            snapshot.tps >= 18.5 -> tickBudget.set(45)
            snapshot.tps >= 17.5 -> tickBudget.set(35)
            snapshot.tps >= 16.5 -> tickBudget.set(25)
            else -> tickBudget.set(15)
        }
    }

    fun currentTickBudget(): Int = tickBudget.get()

    fun calculateTickRate(priority: Int): Int {
        val budget = tickBudget.get()

        return when {
            priority >= 90 -> 1
            priority >= 70 -> if (budget > 40) 1 else 2
            priority >= 50 -> if (budget > 30) 2 else 3
            priority >= 30 -> if (budget > 25) 3 else 5
            else -> if (budget > 25) 5 else 10
        }
    }
}
