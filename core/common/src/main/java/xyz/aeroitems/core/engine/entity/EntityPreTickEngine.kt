package xyz.aeroitems.core.engine.entity

import kotlinx.coroutines.*

object EntityPreTickEngine {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun preTick(task: suspend () -> Unit) {
        scope.launch { task() }
    }
}
