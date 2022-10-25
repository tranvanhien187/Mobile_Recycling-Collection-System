package datn.cnpm.rcsystem.data.entitiy.transport


data class CreateTransportGarbageRequest(
    val customerId: String? = null,
    val exchangeWeight: Float = 0f,
    val street: String? = null,
    val district: String? = null,
    val cityOrProvince: String? = null,
)