package datn.cnpm.rcsystem.feature.history.giftdetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.history.GetGiftHistoryDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftHistoryDetailViewModel @Inject constructor(private val getGiftHistoryDetailUseCase: GetGiftHistoryDetailUseCase) :
    BaseViewModel<GiftHistoryDetailState, GiftHistoryDetailEvent>() {
    override fun initState() = GiftHistoryDetailState()

    fun getGiftHistoryDetail(historyId: String) = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            getGiftHistoryDetailUseCase.getGiftHistoryDetail(GetGiftHistoryDetailUseCase.Parameters(historyId))
        if (response.succeeded) {
            dispatchState(currentState.copy(giftHistoryDetail = response.requireData))
        } else if (response.failed) {
            DebugLog.e(response.requireError.message)
        } else {
            dispatchEvent(GiftHistoryDetailEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
}