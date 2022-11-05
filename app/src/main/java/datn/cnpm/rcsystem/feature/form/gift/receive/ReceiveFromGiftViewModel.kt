package datn.cnpm.rcsystem.feature.form.gift.receive

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import datn.cnpm.rcsystem.domain.usecase.transport.ReceiveTransportGiftFormUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiveFromGiftViewModel @Inject constructor(private val receiveTransportGiftFormUseCase: ReceiveTransportGiftFormUseCase) :
    BaseViewModel<ReceiveFromGiftState, ReceiveFromGiftEvent>() {
    override fun initState() = ReceiveFromGiftState()

    fun getTransportFormDetail(form: TransportFormFirebase?) {
        dispatchState(currentState.copy(form = form))
    }

    fun receiveTransportForm() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            currentState.form?.let {
                val response =
                    receiveTransportGiftFormUseCase.receiveTransportGiftForm(
                        ReceiveTransportGiftFormUseCase.Parameters(
                            it.id,
                            it.customerName,
                            it.customerPhoneNumber
                        )
                    )
                if (response.succeeded) {
                    dispatchEvent(event = ReceiveFromGiftEvent.ReceiveFormSuccess)

                } else if (response.failed) {
                    when (response.requireError.errorCode) {
                        ErrorCode.NOT_FIND_STAFF_ID -> {
                            dispatchEvent(ReceiveFromGiftEvent.ReceiveFormFailure(ErrorCode.NOT_FIND_STAFF_ID))
                        }
                        ErrorCode.FORM_RESOLVED -> {
                            dispatchEvent(ReceiveFromGiftEvent.ReceiveFormFailure(ErrorCode.FORM_RESOLVED))
                        }
                        ErrorCode.NOT_FIND_FORM -> {
                            dispatchEvent(ReceiveFromGiftEvent.ReceiveFormFailure(ErrorCode.NOT_FIND_FORM))
                        }
                        else -> {
                            dispatchEvent(ReceiveFromGiftEvent.ReceiveFormFailure(ErrorCode.UNKNOWN_ERROR))
                        }
                    }
                } else {
                    DebugLog.e(response.requireError.message)
                }
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}