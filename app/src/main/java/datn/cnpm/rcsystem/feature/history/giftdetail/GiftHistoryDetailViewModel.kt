package datn.cnpm.rcsystem.feature.history.giftdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity
import javax.inject.Inject

@HiltViewModel
class GiftHistoryDetailViewModel @Inject constructor() :
    BaseViewModel<GiftHistoryDetailState, GiftHistoryDetailEvent>() {
    override fun initState() = GiftHistoryDetailState()

    private val data = GiftExchangeHistory(
        id = "5em23daw323dc",
        name = "Iphone XX Promax UltraView",
        brand = "Apple",
        agentName = "Agent name",
        contributor = "Digiworld",
        point = 1200,
        evidenceUrl = "https://st.quantrimang.com/photos/image/2021/10/06/co-nen-nang-cap-iphone-x-len-iphone-13.jpg",
        customerName = "Tran Van A",
        status = "Success",
        staffName = "Nguyen Van A",
        createAt = "12/12/2020",
        cancelAt = "12/12/2020",
        completeAt = "12/12/2020",
        receiveAt = "12/12/2020"
    )

//    fun initData(giftExchangeHistory: GiftExchangeHistory) {
//        dispatchState(currentState.copy(giftHistory = giftExchangeHistory))
//    }

    fun getGiftHistoryDetail(): GiftExchangeHistory = data
}