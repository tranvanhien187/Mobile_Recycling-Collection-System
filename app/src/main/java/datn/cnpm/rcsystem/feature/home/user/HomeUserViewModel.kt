package datn.cnpm.rcsystem.feature.home.user

import androidx.lifecycle.viewModelScope
import datn.cnpm.rcsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeUserViewModel @Inject constructor() : BaseViewModel<HomeUserState, HomeUserEvent>() {
    override fun initState() = HomeUserState()

    fun testLoading() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            delay(5000)
            dispatchState(currentState.copy(loading = false))
        }
    }
}