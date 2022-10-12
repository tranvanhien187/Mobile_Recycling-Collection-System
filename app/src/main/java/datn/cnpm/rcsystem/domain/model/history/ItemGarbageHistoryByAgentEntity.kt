package datn.cnpm.rcsystem.domain.model.history

import java.util.*


data class ItemGarbageHistoryByAgentEntity(
 val weight: Float,
 val customerName: String,
 val staffName: String,
 override val id: String,
 override val point: Long,
 override val status: String,
 override val createAt: Date,
 override val receiveAt: Date,
 override val completeAt: Date,
 override val cancelAt: Date
) : BaseItemHistory
