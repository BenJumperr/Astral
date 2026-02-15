package xyz.aeroitems.core.engine.routing

import xyz.aeroitems.core.engine.ai.AILoadPredictor

class SmartRoutingEngine(
    private val predictor: AILoadPredictor
) {

    fun selectBestNode(nodes: Map<String, Double>): String {
        return nodes.minByOrNull {
            predictor.predict(it.key)
        }?.key ?: nodes.keys.first()
    }
}
