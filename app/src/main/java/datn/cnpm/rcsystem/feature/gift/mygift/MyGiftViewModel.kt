package datn.cnpm.rcsystem.feature.gift.mygift

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.gift.GiftCriteria
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftOwnerByAgentUseCase
import datn.cnpm.rcsystem.feature.gift.list.GiftEvent
import datn.cnpm.rcsystem.feature.gift.list.GiftState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyGiftViewModel @Inject constructor(private val getGiftOwnerByAgentUseCase: GetGiftOwnerByAgentUseCase) :
    BaseViewModel<GiftState, GiftEvent>() {
    override fun initState() = GiftState()

    fun fetchMyGift() {
        viewModelScope.launch() {
            dispatchState(currentState.copy(loading = true))
            SingletonObject.agent?.let {
                val response =
                    getGiftOwnerByAgentUseCase.getGiftOwnerByAgent(GetGiftOwnerByAgentUseCase.Parameters(it.id, GiftCriteria.NONE.name))
                if (response.succeeded) {
                    dispatchState(currentState.copy(listGift = response.requireData))
                } else {
                    DebugLog.e(response.requireError.message)
                }
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}