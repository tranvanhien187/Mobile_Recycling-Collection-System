package datn.cnpm.rcsystem.feature.form.garbage.receive

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface ReceiveGarbageFormEvent {
    object ReceiveFormSuccess : ReceiveGarbageFormEvent
    data class ReceiveFormFailure(val error: ErrorCode) : ReceiveGarbageFormEvent
}