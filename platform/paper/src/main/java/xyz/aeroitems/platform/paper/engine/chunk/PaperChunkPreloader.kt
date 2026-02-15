package xyz.aeroitems.platform.paper.engine.chunk

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import xyz.aeroitems.core.engine.chunk.ChunkPreloadEngine

class PaperChunkPreloader(
    private val plugin: JavaPlugin,
    private val engine: ChunkPreloadEngine
) {

    fun start() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable {
            repeat(8) {
                val task = engine.poll() ?: return@Runnable
                val world = Bukkit.getWorld(task.world) ?: return@Runnable
                world.getChunkAtAsync(task.x, task.z, true)
            }
        }, 1L, 1L)
    }
}