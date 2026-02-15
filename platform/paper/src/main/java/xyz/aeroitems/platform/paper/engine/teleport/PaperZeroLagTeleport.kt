package xyz.aeroitems.platform.paper.engine.teleport

import org.bukkit.Bukkit
import xyz.aeroitems.core.engine.teleport.TeleportContext
import xyz.aeroitems.core.engine.teleport.ZeroLagTeleportEngine

class PaperZeroLagTeleport : ZeroLagTeleportEngine {

    override fun prepare(ctx: TeleportContext) {
        val world = Bukkit.getWorld(ctx.world) ?: return
        world.getChunkAtAsync(ctx.x.toInt() shr 4, ctx.z.toInt() shr 4, true)
    }

    override fun commit(ctx: TeleportContext) {
        val player = Bukkit.getPlayer(ctx.playerId) ?: return
        val world = Bukkit.getWorld(ctx.world) ?: return

        player.teleportAsync(
            world.getHighestBlockAt(ctx.x.toInt(), ctx.z.toInt()).location.add(0.5,1.0,0.5)
        )
    }
}
