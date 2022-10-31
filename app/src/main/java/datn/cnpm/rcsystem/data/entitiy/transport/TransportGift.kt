package datn.cnpm.rcsystem.data.entitiy.transport

data class CreateTransportGiftRequest(
    val giftId: String?,
    val customerId: String,
    val street: String,
    val district: String,
    val cityOrProvince: String,
)