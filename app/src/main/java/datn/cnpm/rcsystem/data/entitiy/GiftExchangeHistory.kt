package datn.cnpm.rcsystem.data.entitiy

import datn.cnpm.rcsystem.R

data class GiftExchangeHistory(
  val id: String,
  val name: String? = null,
  val brand: String? = null,
  val contributor: String? = null,
  val point: Int? = null,
  val evidenceUrl: String? = null,
  val customerName: String? = null,
  val agentName: String? = null,
  val staffName: String? = null,
  val status: String? = null,
  val createAt: String? = null,
  val receiveAt: String? = null,
  val completeAt: String? = null,
  val cancelAt: String? = null,
) {

  fun getStatusIcon() : Int {
    return when(status) {
      "Success" -> R.drawable.ic_status_succes
      "Pending" -> R.drawable.ic_status_pending
      else -> R.drawable.ic_status_fail
    }
  }

  fun getStatusColor() : Int{
    return when(status) {
      "Success" -> R.color.green_00ad31
      "Pending" -> R.color.orange_f99200
      else -> R.color.red_f34d4d
    }
  }
}
