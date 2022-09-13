package datn.cnpm.rcsystem.feature.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginState, LoginEvent>() {
    override fun initState() = LoginState()

    fun login(email: String, password: String, isRemember: Boolean) {
//        if(email.validateEmail() != Constant.EMAIL_VALID) {
//
//        }
        dispatchState(currentState.copy(loading = true))
        viewModelScope.launch {
            val response = loginUseCase.login(LoginUseCase.Parameters(email, password, isRemember))
            if (response.succeeded) {
                when (response.requireData.role) {
                    Role.USER.toString() -> {
                        dispatchEvent(LoginEvent.UserLoginSuccess)
                    }
                    Role.DEALER.toString() -> {
                        dispatchEvent(LoginEvent.DealerLoginSuccess)
                    }
                    else -> {}
                }

            } else {
                Log.d("AAA", response.requireError.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}