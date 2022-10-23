package datn.cnpm.rcsystem.feature.gift.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceDetailUseCase
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftDetailViewModel @Inject constructor(private val getGiftDetailUseCase: GetGiftDetailUseCase) :
    BaseViewModel<GiftDetailState, GiftDetailEvent>() {
    override fun initState() = GiftDetailState()

    fun fetchGiftDetail(giftId: String) {
        viewModelScope.launch() {
            dispatchState(currentState.copy(loading = true))
            val response = getGiftDetailUseCase.getGiftDetail(GetGiftDetailUseCase.Parameters(giftId))
            if (response.succeeded) {
                dispatchState(currentState.copy(gift = response.requireData))
            } else {
                DebugLog.e( response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}