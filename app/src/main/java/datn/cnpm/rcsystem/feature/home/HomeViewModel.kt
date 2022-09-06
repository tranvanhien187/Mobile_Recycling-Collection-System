package datn.cnpm.rcsystem.feature.home

import androidx.lifecycle.viewModelScope
import datn.cnpm.rcsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeEvent>() {
    override fun initState() = HomeState()

    fun testLoading() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            delay(5000)
            dispatchState(currentState.copy(loading = false))
        }
    }
}