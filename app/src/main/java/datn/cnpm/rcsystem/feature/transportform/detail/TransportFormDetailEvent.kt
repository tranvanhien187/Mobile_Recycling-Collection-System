package datn.cnpm.rcsystem.feature.transportform.detail

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface TransportFormDetailEvent {
    object ReceiveFormSuccess : TransportFormDetailEvent
    data class ReceiveFormFailure(val error: ErrorCode) : TransportFormDetailEvent
}