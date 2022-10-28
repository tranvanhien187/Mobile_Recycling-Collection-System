package datn.cnpm.rcsystem.feature.form.gift.create

sealed interface CreateFormGiftEvent {
    object CreateTransportGarbageSuccess: CreateFormGiftEvent
}