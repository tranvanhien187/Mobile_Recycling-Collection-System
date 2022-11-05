package datn.cnpm.rcsystem.feature.form.gift.complete

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import datn.cnpm.rcsystem.domain.usecase.transport.CompleteTransportGiftFormUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CompleteGiftFormViewModel @Inject constructor(private val completeTransportGiftFormUseCase: CompleteTransportGiftFormUseCase) :
    BaseViewModel<CompleteGiftFormState, CompleteGiftFormEvent>() {
    override fun initState() = CompleteGiftFormState()

    fun completeForm(formId: String) {
        currentState.file?.let {
            viewModelScope.launch {
                dispatchState(currentState.copy(loading = true))
                val response =
                    completeTransportGiftFormUseCase.completeTransportGarbageForm(
                        CompleteTransportGiftFormUseCase.Parameters(
                            it,
                            formId
                        )
                    )
                if (response.succeeded) {
                    dispatchEvent(CompleteGiftFormEvent.CompleteTransportGarbageSuccess)
                } else {
                    DebugLog.e(response.requireError.message)
                }
                dispatchState(currentState.copy(loading = false))
            }

        } ?: kotlin.run {
            dispatchEvent(CompleteGiftFormEvent.EvidenceEmpty)
            return
        }

    }

    fun setFileEvidence(file: File) {
        dispatchState(currentState.copy(file = file))
    }

    fun setForm(form: TransportFormFirebase?) {
        dispatchState(currentState.copy(form = form))
    }
}