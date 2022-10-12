package datn.cnpm.rcsystem.data.entitiy

import java.util.*


data class GarbageHistoryDetailResponse(
    val id: String? = null,
    val weight: Float = 0f,
    val point: Long = 0,
    val evidenceUrl: String? = null,
    val customerName: String? = null,
    val agentName: String? = null,
    val staffName: String? = null,
    val status: String? = null,
    val createAt: Date? = null,
    val receiveAt: Date? = null,
    val completeAt: Date? = null,
    val cancelAt: Date? = null
)
