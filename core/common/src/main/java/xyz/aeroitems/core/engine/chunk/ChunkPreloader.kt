package xyz.aeroitems.core.engine.chunk

import kotlinx.coroutines.*
import java.util.concurrent.PriorityBlockingQueue

object ChunkPreloader {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val queue = PriorityBlockingQueue<ChunkTicket>(
        128,
        compareByDescending { it.priority }
    )

    fun submit(ticket: ChunkTicket) {
        queue.offer(ticket)
    }

    fun start(worker: suspend (ChunkTicket) -> Unit) {
        repeat(4) {
            scope.launch {
                while (isActive) {
                    val ticket = queue.take()
                    worker(ticket)
                }
            }
        }
    }
}
