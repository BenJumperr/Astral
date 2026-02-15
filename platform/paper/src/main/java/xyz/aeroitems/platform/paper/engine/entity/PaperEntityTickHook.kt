package xyz.aeroitems.platform.paper.engine.entity

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import xyz.aeroitems.core.engine.entity.EntityPreTickEngine

class PaperEntityTickHook(
    private val engine: EntityPreTickEngine
) : Listener {

    @EventHandler
    fun onSpawn(e: EntitySpawnEvent) {
        val entity = e.entity
        // future tick routing buradan yapılacak
    }
}
