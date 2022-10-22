package datn.cnpm.rcsystem.feature.gift.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftDetailViewModel @Inject constructor(private val getTPlaceDetailUseCase: GetTPlaceDetailUseCase) :
    BaseViewModel<GiftDetailState, GiftDetailEvent>() {
    override fun initState() = GiftDetailState()

    fun fetchTPlaceDetail(tplaceId: String) {
        viewModelScope.launch() {
            dispatchState(currentState.copy(loading = true))
            val response = getTPlaceDetailUseCase.getTPlaceDetail(GetTPlaceDetailUseCase.Parameters(tplaceId))
            if (response.succeeded) {
                dispatchState(currentState.copy(tplace = response.requireData))
            } else {
                Log.d("AAAA", response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}