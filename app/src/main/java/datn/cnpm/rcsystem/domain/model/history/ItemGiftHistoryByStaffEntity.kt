package datn.cnpm.rcsystem.domain.model.history

import java.util.*


data class ItemGiftHistoryByStaffEntity(
    val giftName: String,
    val customerName: String,
    val agentName: String,
    override val id: String,
    override val point: Long,
    override val status: String,
    override val createAt: Date,
    override val receiveAt: Date,
    override val completeAt: Date,
    override val cancelAt: Date
) : BaseItemHistory
