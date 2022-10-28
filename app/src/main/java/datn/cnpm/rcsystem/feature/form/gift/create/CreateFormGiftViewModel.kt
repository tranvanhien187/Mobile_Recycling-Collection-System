package datn.cnpm.rcsystem.feature.form.gift.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.gift.GiftDetailEntity
import datn.cnpm.rcsystem.domain.usecase.transport.CreateTransportGarbageFormUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateFormGiftViewModel @Inject constructor(private val createTransportGarbageFormUseCase: CreateTransportGarbageFormUseCase) :
    BaseViewModel<CreateFormGiftState, CreateFormGiftEvent>() {
    override fun initState() = CreateFormGiftState()

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
                dispatchEvent(CreateFormGiftEvent.CreateTransportGarbageSuccess)
            } else {
                DebugLog.e(response.requireError.message)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }

    fun setGift(gift: GiftDetailEntity?) {
        dispatchState(currentState.copy(gift = gift))
    }
}