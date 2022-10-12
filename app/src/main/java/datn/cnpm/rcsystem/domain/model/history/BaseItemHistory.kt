package datn.cnpm.rcsystem.domain.model.history

import java.util.*


interface BaseItemHistory {
    val id: String
    val point: Long
    val status: String
    val createAt: Date
    val receiveAt: Date
    val completeAt: Date
    val cancelAt: Date
}