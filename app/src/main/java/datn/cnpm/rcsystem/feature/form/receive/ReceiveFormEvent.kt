package datn.cnpm.rcsystem.feature.form.receive

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface ReceiveFormEvent {
    object ReceiveFormSuccess : ReceiveFormEvent
    data class ReceiveFormFailure(val error: ErrorCode) : ReceiveFormEvent
}