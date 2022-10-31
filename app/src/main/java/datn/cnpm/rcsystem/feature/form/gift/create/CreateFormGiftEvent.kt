package datn.cnpm.rcsystem.feature.form.gift.create

sealed interface CreateFormGiftEvent {
    object CreateTransportGiftSuccess: CreateFormGiftEvent
    data class CreateTransportGiftFailure(val error: String): CreateFormGiftEvent
}