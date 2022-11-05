package datn.cnpm.rcsystem.feature.history.garbage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.history.GetListGarbageHistoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryViewModel @Inject constructor(private val getListGarbageHistoryUseCase: GetListGarbageHistoryUseCase) :
    BaseViewModel<GarbageHistoryState, GarbageHistoryEvent>() {
    override fun initState() = GarbageHistoryState()

    fun fetchGiftHistory() = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            getListGarbageHistoryUseCase.getListGarbageHistory()
        if (response.succeeded) {
            dispatchState(currentState.copy(garbageHistoryList = response.requireData))
        } else if (response.failed) {
            DebugLog.e(response.requireError.message)
        } else {
            dispatchEvent(GarbageHistoryEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
}