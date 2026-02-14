package xyz.aeroitems.proxy.velocity.network.cluster

data class ClusterNode(
    val identity: NodeIdentity,
    val lastHeartbeat: Long
)
