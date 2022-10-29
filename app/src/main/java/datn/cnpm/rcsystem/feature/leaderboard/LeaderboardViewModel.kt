package datn.cnpm.rcsystem.feature.leaderboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.statistic.GetStatisticTopStaffCollectUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val getStatisticTopStaffCollectUseCase: GetStatisticTopStaffCollectUseCase
) : BaseViewModel<LeaderboardState, LeaderboardEvent>() {
    override fun initState() = LeaderboardState()


    fun getStatisticTopStaffCollect() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                getStatisticTopStaffCollectUseCase.getStatisticTopStaffCollect(
                    GetStatisticTopStaffCollectUseCase.Parameters()
                )
            if (response.succeeded) {
                dispatchState(currentState.copy(listResult = response.requireData))
            } else {
                DebugLog.e(response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}