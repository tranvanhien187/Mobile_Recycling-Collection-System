package datn.cnpm.rcsystem.feature.tradingplace.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceDetailUseCase
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftOwnerByAgentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TradingPlaceDetailViewModel @Inject constructor(
    private val getTPlaceDetailUseCase: GetTPlaceDetailUseCase,
    private val getGiftOwnerByAgentUseCase: GetGiftOwnerByAgentUseCase,
) : BaseViewModel<TradingPlaceDetailState, TradingPlaceDetailEvent>() {
    override fun initState() = TradingPlaceDetailState()

    companion object {
        private const val CRITERIA_AVAILABLE = "AVAILABLE"
    }

    fun fetchTPlaceDetail(tplaceId: String) {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                getTPlaceDetailUseCase.getTPlaceDetail(GetTPlaceDetailUseCase.Parameters(tplaceId))
            if (response.succeeded) {
                dispatchState(currentState.copy(tplace = response.requireData))
            } else {
                DebugLog.e(response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }

    fun fetchGiftByAgentAdapter(ownerId: String) {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                getGiftOwnerByAgentUseCase.getGiftOwnerByAgent(GetGiftOwnerByAgentUseCase.Parameters(ownerId, CRITERIA_AVAILABLE))
            if (response.succeeded) {
                dispatchState(currentState.copy(listGiftOwnerByAgent = response.requireData.toMutableList()))
            } else {
                DebugLog.e(response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}