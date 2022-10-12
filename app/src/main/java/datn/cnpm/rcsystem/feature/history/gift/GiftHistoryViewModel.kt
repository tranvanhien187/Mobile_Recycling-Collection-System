package datn.cnpm.rcsystem.feature.history.gift

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetListGiftHistoryUseCase
import datn.cnpm.rcsystem.feature.history.garbage.GarbageHistoryEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftHistoryViewModel @Inject constructor(private val getListGiftHistoryUseCase: GetListGiftHistoryUseCase) :
    BaseViewModel<GiftHistoryState, GiftHistoryEvent>() {
    override fun initState() = GiftHistoryState()

    fun fetchGiftHistory() = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            getListGiftHistoryUseCase.getListGiftHistory()
        if (response.succeeded) {
            dispatchState(currentState.copy(giftHistoryList = response.requireData))
        } else if (response.failed) {
            DebugLog.e(response.requireError.message)
        } else {
            dispatchEvent(GiftHistoryEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
}