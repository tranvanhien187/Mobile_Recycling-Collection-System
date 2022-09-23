package datn.cnpm.rcsystem.feature.history.gift

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetGiftUserHistoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftHistoryViewModel @Inject constructor(private val getGiftUserHistoryUseCase: GetGiftUserHistoryUseCase) :
    BaseViewModel<GiftHistoryState, GiftHistoryEvent>() {
    override fun initState() = GiftHistoryState()

    fun fetchGiftHistory() = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            getGiftUserHistoryUseCase.getGiftUserHistory()
        if (response.succeeded) {
            dispatchState(currentState.copy(giftList = response.requireData))
        } else if (response.failed) {
            Log.d("AAAAA",response.requireError.message.toString())
        } else {
            dispatchEvent(GiftHistoryEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
}