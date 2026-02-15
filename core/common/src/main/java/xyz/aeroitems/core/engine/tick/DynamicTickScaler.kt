package xyz.aeroitems.core.engine.tick

class DynamicTickScaler {

    fun calculateTickRate(load: Double): Double {
        return when {
            load < 0.4 -> 20.0
            load < 0.7 -> 18.0
            load < 0.9 -> 16.0
            else -> 14.0
        }
    }
}
