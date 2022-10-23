package datn.cnpm.rcsystem.feature.tradingplace.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceForUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TradingPlaceViewModel @Inject constructor(private val getTPlaceForUserUseCase: GetTPlaceForUserUseCase) :
    BaseViewModel<TradingPlaceState, TradingPlaceEvent>() {
    override fun initState() = TradingPlaceState()

    fun fetchTPlaceForUser() {
        viewModelScope.launch() {
            dispatchState(currentState.copy(loading = true))
            val response =
                getTPlaceForUserUseCase.getTPlaceForUser(GetTPlaceForUserUseCase.Parameters())
            if (response.succeeded) {
                dispatchState(currentState.copy(listPlace = response.requireData))
            } else {
                DebugLog.e(response.requireError.message)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}