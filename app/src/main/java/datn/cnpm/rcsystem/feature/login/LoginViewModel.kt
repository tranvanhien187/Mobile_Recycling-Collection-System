package datn.cnpm.rcsystem.feature.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.Constant
import datn.cnpm.rcsystem.common.utils.CommonUtils.validateEmail
import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginState, LoginEvent>() {
    override fun initState() = LoginState()

    fun login() {
        dispatchState(currentState.copy(loading = true))
        viewModelScope.launch {
//            val response = loginUseCase.login(LoginUseCase.Parameters())
//            if (response.succeeded) {
//                Log.d("AAA", response.requireData[0].productName.toString())
//            } else {
//                Log.d("AAA", response.requireError.message.toString())
//            }
            dispatchState(currentState.copy(loading = false))
        }
    }

    fun login(email: String, password: String) {
        if(email.validateEmail() != Constant.EMAIL_VALID) {

        }
        dispatchState(currentState.copy(loading = true))
        viewModelScope.launch {
            val response = loginUseCase.login(LoginUseCase.Parameters(email, password))
            if (response.succeeded) {
                Log.d("AAA", response.requireData[0].productName.toString())
            } else {
                Log.d("AAA", response.requireError.message.toString())
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}