package xyz.aeroitems.proxy.velocity.network.routing

import xyz.aeroitems.proxy.velocity.network.cluster.NodeRegistry
import xyz.aeroitems.proxy.velocity.network.cluster.NodeType

object LoadBalancer {

    fun selectBest(type: NodeType): String? {
        return NodeRegistry.getByType(type)
            .minByOrNull {
                it.identity.id.hashCode()
            }?.identity?.id
    }
}