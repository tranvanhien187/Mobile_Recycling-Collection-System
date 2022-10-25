package datn.cnpm.rcsystem.feature.form.create

sealed interface CreateFormEvent {
    object CreateTransportGarbageSuccess: CreateFormEvent
}