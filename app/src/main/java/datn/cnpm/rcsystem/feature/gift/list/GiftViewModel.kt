package datn.cnpm.rcsystem.feature.gift.list

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetTPlaceForUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftViewModel @Inject constructor(private val getTPlaceForUserUseCase: GetTPlaceForUserUseCase) :
    BaseViewModel<GiftState, GiftEvent>() {
    override fun initState() = GiftState()

    fun fetchTPlaceForUser() {
        viewModelScope.launch() {
            dispatchState(currentState.copy(loading = true))
            val response = getTPlaceForUserUseCase.getTPlaceForUser(GetTPlaceForUserUseCase.Parameters())
            if (response.succeeded) {
                dispatchState(currentState.copy(listPlace = response.requireData))
            } else {
                Log.d("AAAA", response.requireError.exception.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}