package datn.cnpm.rcsystem.feature.form

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.transport.CreateTransportGarbageFormUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateFormViewModel @Inject constructor(private val createTransportGarbageFormUseCase: CreateTransportGarbageFormUseCase) :
    BaseViewModel<CreateFormState, CreateFormEvent>() {
    override fun initState() = CreateFormState()

    fun createTransportGarbage(
        weight: Float,
        street: String,
        district: String,
        cityOrProvince: String
    ) {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                createTransportGarbageFormUseCase.createTransportGarbageForm(
                    CreateTransportGarbageFormUseCase.Parameters(
                        weight,
                        street,
                        district,
                        cityOrProvince
                    )
                )
            if (response.succeeded) {
                dispatchEvent(CreateFormEvent.CreateTransportGarbageSuccess)
            } else {
                DebugLog.e(response.requireError.message)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}