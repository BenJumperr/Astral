package xyz.aeroitems.proxy.velocity.network.routing

import xyz.aeroitems.proxy.velocity.network.cluster.NodeRegistry
import xyz.aeroitems.proxy.velocity.network.cluster.NodeType

object SmartRouter {

    fun resolve(policy: RountingPolicy): List<String> {
        return when (policy) {
            RountingPolicy.ANY_PROXY ->
                listOfNotNull(LoadBalancer.selectBest(NodeType.PROXY))

            RountingPolicy.ANY_PAPER ->
                listOfNotNull(LoadBalancer.selectBest(NodeType.PAPER))

            RountingPolicy.ANY_AI ->
                listOfNotNull(LoadBalancer.selectBest(NodeType.AI))

            RountingPolicy.ANY_SCHEDULER ->
                listOfNotNull(LoadBalancer.selectBest(NodeType.SCHEDULER))

            RountingPolicy.ALL_PROXY ->
                NodeRegistry.getByType(NodeType.PROXY).map { it.identity.id }

            RountingPolicy.ALL_PAPER ->
                NodeRegistry.getByType(NodeType.PAPER).map { it.identity.id }

            RountingPolicy.BROADCAST ->
                NodeRegistry.getAll().map { it.identity.id }
        }
    }
}