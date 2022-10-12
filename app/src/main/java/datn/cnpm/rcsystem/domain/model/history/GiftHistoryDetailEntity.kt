package datn.cnpm.rcsystem.domain.model.history

import java.util.*


data class GiftHistoryDetailEntity(
    val id: String? = null,
    val name: String? = null,
    val brand: String? = null,
    val contributor: String? = null,
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

fun GiftHistoryDetailResponse.mapToEntity() = GiftHistoryDetailEntity(
    id,
    name,
    brand,
    contributor,
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
