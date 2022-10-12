package datn.cnpm.rcsystem.domain.model.history

import java.util.*


data class ItemGiftHistoryByAgentEntity(
    val giftName: String,
    val staffName: String? = null,
    val customerName: String? = null,
    override val id: String,
    override val point: Long,
    override val status: String,
    override val createAt: Date,
    override val receiveAt: Date,
    override val completeAt: Date,
    override val cancelAt: Date
): BaseItemHistory