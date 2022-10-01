package datn.cnpm.rcsystem.feature.history.garbagedetail

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity
import datn.cnpm.rcsystem.domain.usecase.GetGarbageUserHistoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryDetailViewModel @Inject constructor() :
    BaseViewModel<GarbageHistoryDetailState, GarbageHistoryDetailEvent>() {
    override fun initState() = GarbageHistoryDetailState()

    fun initData(garbageUserHistoryEntity: GarbageUserHistoryEntity) {
        dispatchState(currentState.copy(garbageHistory = garbageUserHistoryEntity))
    }
}