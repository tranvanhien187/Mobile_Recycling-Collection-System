package datn.cnpm.rcsystem.feature.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.domain.usecase.RegisterUseCase
import datn.cnpm.rcsystem.feature.changepassword.ChangePasswordEvent
import datn.cnpm.rcsystem.feature.changepassword.ChangePasswordState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    BaseViewModel<ChangePasswordState, ChangePasswordEvent>() {
    override fun initState() = ChangePasswordState()

    fun register() {
        viewModelScope.launch {

        }
    }
}