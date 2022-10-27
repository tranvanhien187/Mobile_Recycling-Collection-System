package datn.cnpm.rcsystem.feature.form.complete

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportForm
import datn.cnpm.rcsystem.domain.usecase.transport.CompleteTransportFormUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CompleteFormViewModel @Inject constructor(private val completeTransportFormUseCase: CompleteTransportFormUseCase) :
    BaseViewModel<CompleteFormState, CompleteFormEvent>() {
    override fun initState() = CompleteFormState()

    fun completeForm(weight: String, formId: String) {
        currentState.file?.let {
            viewModelScope.launch {
                dispatchState(currentState.copy(loading = true))
                val response =
                    completeTransportFormUseCase.completeTransportGarbageForm(
                        CompleteTransportFormUseCase.Parameters(
                            it,
                            weight,
                            formId
                        )
                    )
                if (response.succeeded) {
                    dispatchEvent(CompleteFormEvent.CompleteTransportGarbageSuccess)
                } else {
                    DebugLog.e(response.requireError.message)
                }
                dispatchState(currentState.copy(loading = false))
            }

        } ?: kotlin.run {
            dispatchEvent(CompleteFormEvent.EvidenceEmpty)
            return
        }

    }

    fun setFileEvidence(file: File) {
        dispatchState(currentState.copy(file = file))
    }

    fun setForm(form: TransportForm?) {
        dispatchState(currentState.copy(form = form))
    }
}