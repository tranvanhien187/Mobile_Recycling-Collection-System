package datn.cnpm.rcsystem.data.entitiy

data class GarbageExchange(
  val id : Int? = null,
  val weight: Float? = null,
  val point: Int? = null,
  val evidenceUrl: String? = null,
  val customerName: String? = null,
  val agentName: String? = null,
  val staffName: String? = null,
  val status: String? = null,
  val createAt: String? = null,
  val receiveAt: String? = null,
  val completeAt: String? = null,
  val cancelAt: String? = null
)
