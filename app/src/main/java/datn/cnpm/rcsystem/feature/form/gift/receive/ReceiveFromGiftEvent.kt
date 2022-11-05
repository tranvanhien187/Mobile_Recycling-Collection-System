package datn.cnpm.rcsystem.feature.form.gift.receive

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface ReceiveFromGiftEvent {
    object ReceiveFormSuccess : ReceiveFromGiftEvent
    data class ReceiveFormFailure(val error: ErrorCode) : ReceiveFromGiftEvent
}