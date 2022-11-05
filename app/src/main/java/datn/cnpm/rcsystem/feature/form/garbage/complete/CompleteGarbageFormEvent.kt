package datn.cnpm.rcsystem.feature.form.garbage.complete

sealed interface CompleteGarbageFormEvent {
    object CompleteTransportGarbageSuccess: CompleteGarbageFormEvent
    object EvidenceEmpty: CompleteGarbageFormEvent
}