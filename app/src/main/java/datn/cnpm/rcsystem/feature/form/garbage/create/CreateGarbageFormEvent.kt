package datn.cnpm.rcsystem.feature.form.garbage.create

sealed interface CreateGarbageFormEvent {
    object CreateTransportGarbageSuccess: CreateGarbageFormEvent
}