package datn.cnpm.rcsystem.feature.history.giftdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity
import javax.inject.Inject

@HiltViewModel
class GiftHistoryDetailViewModel @Inject constructor() :
    BaseViewModel<GiftHistoryDetailState, GiftHistoryDetailEvent>() {
    override fun initState() = GiftHistoryDetailState()

    fun initData(giftUserHistoryEntity: GiftUserHistoryEntity) {
        dispatchState(currentState.copy(giftHistory = giftUserHistoryEntity))
    }
}