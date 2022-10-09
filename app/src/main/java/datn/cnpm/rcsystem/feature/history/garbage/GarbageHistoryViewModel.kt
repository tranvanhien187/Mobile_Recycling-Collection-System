package datn.cnpm.rcsystem.feature.history.garbage

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.domain.usecase.GetGarbageUserHistoryUseCase
import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryViewModel @Inject constructor(private val getGarbageUserHistoryUseCase: GetGarbageUserHistoryUseCase) :
    BaseViewModel<GarbageHistoryState, GarbageHistoryEvent>() {
    override fun initState() = GarbageHistoryState()

//    fun fetchGiftHistory() = viewModelScope.launch {
//        dispatchState(currentState.copy(loading = true))
//        val response =
//            getGarbageUserHistoryUseCase.getGarbageUserHistory()
//        if (response.succeeded) {
//            dispatchState(currentState.copy(garbageList = response.requireData))
//        } else if (response.failed) {
//            Log.d("AAAAA",response.requireError.message.toString())
//        } else {
//            dispatchEvent(GarbageHistoryEvent.UnKnowError)
//        }
//        dispatchState(currentState.copy(loading = false))
//    }
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
    }