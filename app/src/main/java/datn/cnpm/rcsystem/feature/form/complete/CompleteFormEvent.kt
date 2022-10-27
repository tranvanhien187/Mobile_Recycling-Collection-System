package datn.cnpm.rcsystem.feature.form.complete

sealed interface CompleteFormEvent {
    object CompleteTransportGarbageSuccess: CompleteFormEvent
    object EvidenceEmpty: CompleteFormEvent
}