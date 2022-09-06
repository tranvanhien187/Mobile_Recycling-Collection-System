package datn.cnpm.rcsystem.feature.register

import androidx.lifecycle.viewModelScope
import datn.cnpm.rcsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel<RegisterState, RegisterEvent>() {
    override fun initState() = RegisterState()

    fun login() {
        viewModelScope.launch {

        }
    }
}