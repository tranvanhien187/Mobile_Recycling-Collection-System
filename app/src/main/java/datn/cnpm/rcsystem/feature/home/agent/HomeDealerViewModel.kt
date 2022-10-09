package datn.cnpm.rcsystem.feature.home.agent

import androidx.lifecycle.viewModelScope
import datn.cnpm.rcsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDealerViewModel @Inject constructor() : BaseViewModel<HomeDealerState, HomeDealerEvent>() {
    override fun initState() = HomeDealerState()

    fun testLoading() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            delay(5000)
            dispatchState(currentState.copy(loading = false))
        }
    }
}