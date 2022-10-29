package datn.cnpm.rcsystem.domain.model.history

import datn.cnpm.rcsystem.data.entitiy.history.GarbageHistoryDetailResponse
import java.util.*


data class GarbageHistoryDetailEntity(
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

fun GarbageHistoryDetailResponse.mapToEntity() = GarbageHistoryDetailEntity(
    id,
    weight,
    point,
    evidenceUrl,
    customerName,
    agentName,
    staffName,
    status,
    createAt,
    receiveAt,
    completeAt,
    cancelAt
)