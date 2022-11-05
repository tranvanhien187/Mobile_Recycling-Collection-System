package datn.cnpm.rcsystem.feature.form.garbage.complete

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import datn.cnpm.rcsystem.domain.usecase.transport.CompleteTransportGarbageFormUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CompleteGarbageFormViewModel @Inject constructor(private val completeTransportFormUseCase: CompleteTransportGarbageFormUseCase) :
    BaseViewModel<CompleteGarbageFormState, CompleteGarbageFormEvent>() {
    override fun initState() = CompleteGarbageFormState()

    fun completeForm(weight: String, formId: String) {
        currentState.file?.let {
            viewModelScope.launch {
                dispatchState(currentState.copy(loading = true))
                val response =
                    completeTransportFormUseCase.completeTransportGarbageForm(
                        CompleteTransportGarbageFormUseCase.Parameters(
                            it,
                            weight,
                            formId
                        )
                    )
                if (response.succeeded) {
                    dispatchEvent(CompleteGarbageFormEvent.CompleteTransportGarbageSuccess)
                } else {
                    DebugLog.e(response.requireError.message)
                }
                dispatchState(currentState.copy(loading = false))
            }

        } ?: kotlin.run {
            dispatchEvent(CompleteGarbageFormEvent.EvidenceEmpty)
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