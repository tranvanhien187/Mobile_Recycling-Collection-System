package datn.cnpm.rcsystem.feature.history.gift

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.domain.usecase.GetGiftUserHistoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftHistoryViewModel @Inject constructor(private val getGiftUserHistoryUseCase: GetGiftUserHistoryUseCase) :
    BaseViewModel<GiftHistoryState, GiftHistoryEvent>() {
    override fun initState() = GiftHistoryState()

    private val data = listOf(
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
        ),
        GiftExchangeHistory(
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
    )

    fun getData() = data

//    fun fetchGiftHistory() = viewModelScope.launch {
//        dispatchState(currentState.copy(loading = true))
//        val response =
//            getGiftUserHistoryUseCase.getGiftUserHistory()
//        if (response.succeeded) {
//            dispatchState(currentState.copy(giftList = response.requireData))
//        } else if (response.failed) {
//            Log.d("AAAAA",response.requireError.message.toString())
//        } else {
//            dispatchEvent(GiftHistoryEvent.UnKnowError)
//        }
//        dispatchState(currentState.copy(loading = false))
//    }
}