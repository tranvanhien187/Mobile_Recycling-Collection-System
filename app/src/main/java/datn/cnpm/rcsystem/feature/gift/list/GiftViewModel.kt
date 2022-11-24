package datn.cnpm.rcsystem.feature.gift.list

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.gift.GiftEntity
import datn.cnpm.rcsystem.domain.usecase.GetGiftByCriteriaUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class GiftViewModel @Inject constructor(private val getGiftByCriteriaUseCase: GetGiftByCriteriaUseCase) :
    BaseViewModel<GiftState, GiftEvent>() {
    override fun initState() = GiftState()

    private lateinit var giftList: List<GiftEntity>
    private var category: GiftCategoryModel? = null
    private var searchKeyWord: String = ""
    fun fetchGift() {
        viewModelScope.launch() {
            val response =
                getGiftByCriteriaUseCase.getGiftByCriteria(GetGiftByCriteriaUseCase.Parameters())
            if (response.succeeded) {
                giftList = response.requireData
                dispatchState(currentState.copy(listGift = giftList))
            } else {
                DebugLog.e(response.requireError.message)
            }
        }
    }

    fun initList() {
        dispatchState(currentState.copy(listGift = giftList))
    }

    private lateinit var searchJob: Job
    private lateinit var filterJob: Job

    fun performSearch(keyWord: String) {
        if (::searchJob.isInitialized && searchJob.isActive) {
            searchJob.cancel()
        }

        if (::giftList.isInitialized) {
            searchJob = viewModelScope.launch(Dispatchers.Default) {
                delay(1000)
                val result = giftList.filter { gift ->
                    (gift.name?.contains(keyWord, true)
                        ?: false) && (category?.let { gift.type.equals(it.category.value) }
                        ?: kotlin.run { true })
                }
                searchKeyWord = keyWord
                withContext(Dispatchers.Main) {
                    dispatchState(currentState.copy(listGift = result))
                }
            }
        }
    }

    fun filter(data: GiftCategoryModel?) {
        if (::filterJob.isInitialized && filterJob.isActive) {
            filterJob.cancel()
        }

        if (::giftList.isInitialized) {
            filterJob = viewModelScope.launch(Dispatchers.Default) {
                delay(1000)
                category = data
                val result = giftList.filter { gift ->
                    (gift.name?.contains(searchKeyWord, true)
                        ?: false) && (category?.let { gift.type.equals(it.category.value) }
                        ?: kotlin.run { true })
                }
                withContext(Dispatchers.Main) {
                    dispatchState(currentState.copy(listGift = result))
                }
            }
        }
    }
}