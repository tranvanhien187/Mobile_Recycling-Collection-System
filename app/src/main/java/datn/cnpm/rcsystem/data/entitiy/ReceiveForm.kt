package datn.cnpm.rcsystem.data.entitiy


data class ReceiveFormRequest(
    private val staffId: String,
    private val historyGarbageId: String,
    private val customerName: String,
    private val customerPhoneNumber: String,
)