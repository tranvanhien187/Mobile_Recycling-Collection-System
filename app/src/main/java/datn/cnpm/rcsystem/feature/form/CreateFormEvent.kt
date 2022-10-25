package datn.cnpm.rcsystem.feature.form

sealed interface CreateFormEvent {
    object CreateTransportGarbageSuccess: CreateFormEvent
}