package datn.cnpm.rcsystem.feature.history.garbagedetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.history.GetGarbageHistoryDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryDetailViewModel @Inject constructor(private val garbageHistoryDetailUseCase: GetGarbageHistoryDetailUseCase) :
    BaseViewModel<GarbageHistoryDetailState, GarbageHistoryDetailEvent>() {
    override fun initState() = GarbageHistoryDetailState()

    fun getGarbageHistoryDetail(historyId: String) = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            garbageHistoryDetailUseCase.getGarbageHistoryDetail(
                GetGarbageHistoryDetailUseCase.Parameters(
                    historyId
                )
            )
        if (response.succeeded) {
            dispatchState(currentState.copy(garbageHistoryDetail = response.requireData))
        } else if (response.failed) {
            DebugLog.e(response.requireError.message)
        } else {
            dispatchEvent(GarbageHistoryDetailEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
}