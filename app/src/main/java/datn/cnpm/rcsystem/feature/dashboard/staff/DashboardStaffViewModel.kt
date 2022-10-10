package datn.cnpm.rcsystem.feature.dashboard.staff

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetStaffInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardStaffViewModel @Inject constructor(
    private val getStaffInfoUseCase: GetStaffInfoUseCase
) :
    BaseViewModel<DashboardStaffState, DashboardStaffEvent>() {
    override fun initState() = DashboardStaffState()


    fun fetchStaffInfo() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                getStaffInfoUseCase.getStaffInfo()
            if (response.succeeded) {
                dispatchState(state = currentState.copy(staff = response.requireData))
            } else if (response.failed) {
                when (response.requireError.errorCode) {
                    ErrorCode.NOT_FIND_STAFF_ID -> {
                        DebugLog.e(ErrorCode.NOT_FIND_STAFF_ID.value)
                    }
                    else -> {
                        DebugLog.e(ErrorCode.UNKNOWN_ERROR.value)
                    }
                }
            } else {
                DebugLog.e(response.requireError.message)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}