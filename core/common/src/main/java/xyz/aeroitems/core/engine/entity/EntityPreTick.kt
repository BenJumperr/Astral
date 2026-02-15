package xyz.aeroitems.core.engine.entity

class EntityPreTick {

    fun shouldTick(entityType: String, distance: Double, load: Double): Boolean {
        if (load > 0.8 && distance > 24) return false
        if (load > 0.9 && distance > 16) return false
        return true
    }
}