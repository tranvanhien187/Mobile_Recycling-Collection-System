package datn.cnpm.rcsystem.data.entitiy.transport


data class ReceiveFormGarbageRequest(
    private val staffId: String,
    private val historyGarbageId: String,
    private val customerName: String,
    private val customerPhoneNumber: String,
)

data class ReceiveFormGiftRequest(
    private val staffId: String,
    private val historyGiftId: String,
    private val customerName: String,
    private val customerPhoneNumber: String,
)