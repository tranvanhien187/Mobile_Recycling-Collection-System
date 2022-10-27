package datn.cnpm.rcsystem.feature.form.receive

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportForm
import datn.cnpm.rcsystem.domain.usecase.transport.ReceiveTransportFormUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiveFormViewModel @Inject constructor(private val receiveTransportFormUseCase: ReceiveTransportFormUseCase) :
    BaseViewModel<ReceiveFormState, ReceiveFormEvent>() {
    override fun initState() = ReceiveFormState()

    fun getTransportFormDetail(form: TransportForm?) {
        dispatchState(currentState.copy(form = form))
    }

    fun receiveTransportForm() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            currentState.form?.let {
                val response =
                    receiveTransportFormUseCase.receiveTransportForm(
                        ReceiveTransportFormUseCase.Parameters(
                            it.id,
                            it.customerName,
                            it.customerPhoneNumber
                        )
                    )
                if (response.succeeded) {
                    dispatchEvent(event = ReceiveFormEvent.ReceiveFormSuccess)

                } else if (response.failed) {
                    when (response.requireError.errorCode) {
                        ErrorCode.NOT_FIND_STAFF_ID -> {
                            dispatchEvent(ReceiveFormEvent.ReceiveFormFailure(ErrorCode.NOT_FIND_STAFF_ID))
                        }
                        ErrorCode.FORM_RESOLVED -> {
                            dispatchEvent(ReceiveFormEvent.ReceiveFormFailure(ErrorCode.FORM_RESOLVED))
                        }
                        ErrorCode.NOT_FIND_FORM -> {
                            dispatchEvent(ReceiveFormEvent.ReceiveFormFailure(ErrorCode.NOT_FIND_FORM))
                        }
                        else -> {
                            dispatchEvent(ReceiveFormEvent.ReceiveFormFailure(ErrorCode.UNKNOWN_ERROR))
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