package datn.cnpm.rcsystem.feature.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.Constant
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    BaseViewModel<RegisterState, RegisterEvent>() {
    override fun initState() = RegisterState()

    fun register(email: String, username: String, password: String, retypePassword: String) {
        viewModelScope.launch {
            val matcherEmailFormat: Matcher =
                Pattern.compile(Constant.ALPHANUMERIC_EMAIL_FORMAT).matcher(email)
            val errorEmail = if (matcherEmailFormat.matches()) null else ErrorCode.INVALID_EMAIL
            val errorUsername = if (username.length > 6) null else ErrorCode.SHORT_USERNAME
            val errorPassword = if (password.length > 6) null else ErrorCode.SHORT_PASSWORD
            val errorRetypePassword =
                if (retypePassword == password) null else ErrorCode.RETYPE_PASSWORD
            if (errorEmail == null && errorPassword == null && errorRetypePassword == null && errorUsername == null) {
                dispatchState(currentState.copy(loading = true))
                val response = registerUseCase.register(
                    RegisterUseCase.Parameters(
                        username,
                        email,
                        password,
                        Role.USER.toString()
                    )
                )
                dispatchEvent(RegisterEvent.ValidateField())
                if (response.failed) {
                    dispatchState(currentState.copy(loading = false))
                    when (response.requireError.errorCode) {
                        ErrorCode.EXISTED_EMAIL.toString() -> {
                            dispatchEvent(RegisterEvent.ValidateField(ErrorCode.EXISTED_EMAIL))
                        }
                        ErrorCode.EXISTED_USERNAME.toString() -> {
                            dispatchEvent(RegisterEvent.ValidateField(errorUsername = ErrorCode.EXISTED_USERNAME))
                        }
                        else -> {
                            dispatchEvent(RegisterEvent.RegisterFailure(response.requireError.message))
                        }
                    }

                } else {
                    dispatchState(currentState.copy(loading = false))
                    dispatchEvent(RegisterEvent.RegisterSuccess)
                }
            } else {
                dispatchEvent(
                    RegisterEvent.ValidateField(
                        errorEmail,
                        errorUsername,
                        errorPassword,
                        errorRetypePassword
                    )
                )
            }
        }
    }
}