package xyz.aeroitems.core.engine

import xyz.aeroitems.core.engine.performance.activation.SmartActivationEngine
import xyz.aeroitems.core.engine.performance.prediction.AILoadPredictor
import xyz.aeroitems.core.engine.performance.tick.DynamicTickEngine

class AstralPerformanceEngine {

    val predictor = AILoadPredictor()
    val tickEngine = DynamicTickEngine()
    val activationEngine = SmartActivationEngine(tickEngine)
}