package xyz.aeroitems.proxy.velocity.network.cluster

import java.util.concurrent.ConcurrentHashMap

object NodeRegistry {

    private val nodes = ConcurrentHashMap<String, ClusterNode>()

    fun update(node: NodeIdentity) {
        nodes[node.id] = ClusterNode(node, System.currentTimeMillis())
    }

    fun remove(nodeId: String) {
        nodes.remove(nodeId)
    }

    fun getAll(): Collection<ClusterNode> = nodes.values

    fun getByType(type: NodeType): List<ClusterNode> =
        nodes.values.filter { it.identity.type == type }

    fun size(): Int = nodes.size
}
