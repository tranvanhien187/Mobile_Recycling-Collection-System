package datn.cnpm.rcsystem.feature.dashboard.customer

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetGiftRandom6UserCase
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceRandom6UseCase
import datn.cnpm.rcsystem.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardCustomerViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getTPlaceRandom6UseCase: GetTPlaceRandom6UseCase,
    private val getGiftRandom6UserCase: GetGiftRandom6UserCase
) :
    BaseViewModel<DashboardCustomerState, DashboardCustomerEvent>() {
    override fun initState() = DashboardCustomerState()

    fun getUserInfo() {
        viewModelScope.launch {
            val response = getUserInfoUseCase.getUserInfo()
            if (response.succeeded) {
                dispatchState(currentState.copy(userEntity = response.requireData))
            }
        }
    }

    fun fetchRandomTPlace() {
        viewModelScope.launch {
            val response = getTPlaceRandom6UseCase.getTPlaceRandom6()
            if (response.succeeded) {
                dispatchState(currentState.copy(tPlaceList = response.requireData))
            }
        }
    }

    fun fetchRandomGift() {
        viewModelScope.launch {
            val response = getGiftRandom6UserCase.getGiftRandom6()
            if (response.succeeded) {
                dispatchState(currentState.copy(giftList = response.requireData))
            }
        }
    }
}