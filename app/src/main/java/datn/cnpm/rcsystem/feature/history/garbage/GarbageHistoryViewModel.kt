package datn.cnpm.rcsystem.feature.history.garbage

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.domain.usecase.GetGarbageUserHistoryUseCase
import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryViewModel @Inject constructor(private val getGarbageUserHistoryUseCase: GetGarbageUserHistoryUseCase) :
    BaseViewModel<GarbageHistoryState, GarbageHistoryEvent>() {
    override fun initState() = GarbageHistoryState()

    fun fetchGiftHistory() = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val response =
            getGarbageUserHistoryUseCase.getGarbageUserHistory()
        if (response.succeeded) {
            dispatchState(currentState.copy(garbageList = response.requireData))
        } else if (response.failed) {
            Log.d("AAAAA",response.requireError.message.toString())
        } else {
            dispatchEvent(GarbageHistoryEvent.UnKnowError)
        }
        dispatchState(currentState.copy(loading = false))
    }
    }