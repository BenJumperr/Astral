package xyz.aeroitems.core.engine.teleport

import xyz.aeroitems.core.engine.chunk.ChunkPreloader
import xyz.aeroitems.core.engine.chunk.ChunkTicket
import kotlin.math.floor

object ZeroLagTeleport {

    fun prepare(world: String, x: Double, z: Double) {
        val cx = floor(x/ 16.0).toInt()
        val cz = floor(z/ 16.0).toInt()

        for (dx in -2..2) {
            for (dz in -2..2) {
                ChunkPreloader.submit(
                    ChunkTicket(world, cx + dx, cz + dz, 100)
                )
            }
        }
    }
}