package datn.cnpm.rcsystem.feature.form.gift.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.gift.GiftDetailEntity
import datn.cnpm.rcsystem.domain.usecase.transport.CreateTransportGarbageFormUseCase
import datn.cnpm.rcsystem.domain.usecase.transport.CreateTransportGiftFormUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateFormGiftViewModel @Inject constructor(private val createTransportGiftFormUseCase: CreateTransportGiftFormUseCase) :
    BaseViewModel<CreateFormGiftState, CreateFormGiftEvent>() {
    override fun initState() = CreateFormGiftState()

    fun createTransportGarbage(
        street: String,
        district: String,
        cityOrProvince: String
    ) {
        currentState.gift?.let {
            if(it.redemptionPoint < (SingletonObject.customer?.point?.remainPoint ?: 0)) {
                viewModelScope.launch {
                    dispatchState(currentState.copy(loading = true))
                    val response =
                        createTransportGiftFormUseCase.createTransportGiftForm(
                            CreateTransportGiftFormUseCase.Parameters(
                                it.id,
                                street,
                                district,
                                cityOrProvince
                            )
                        )
                    if (response.succeeded) {
                        SingletonObject.customer?.point?.usePoint(it.redemptionPoint)
                        dispatchEvent(CreateFormGiftEvent.CreateTransportGiftSuccess)
                    } else {
                        dispatchEvent(CreateFormGiftEvent.CreateTransportGiftFailure(response.requireError.message))
                        DebugLog.e(response.requireError.message)
                    }
                    dispatchState(currentState.copy(loading = false))
                }
            }

        }

    }

    fun setGift(gift: GiftDetailEntity?) {
        dispatchState(currentState.copy(gift = gift))
    }
}