package datn.cnpm.rcsystem.feature.dashboard.agent

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.agent.GetAgentInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardAgentViewModel @Inject constructor(
    private val getAgentInfoUseCase: GetAgentInfoUseCase
) :
    BaseViewModel<DashboardAgentState, DashboardAgentEvent>() {
    override fun initState() = DashboardAgentState()

    fun getAgentInfo() {
        viewModelScope.launch {
            val response = getAgentInfoUseCase.getAgentInfo()
            if (response.succeeded) {
                dispatchState(currentState.copy(agentEntity = response.requireData))
            } else {
                DebugLog.e(response.requireError.message)
            }
        }
    }
}