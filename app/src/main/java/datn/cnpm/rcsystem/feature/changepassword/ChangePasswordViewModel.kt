package datn.cnpm.rcsystem.feature.changepassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    BaseViewModel<ChangePasswordState, ChangePasswordEvent>() {
    override fun initState() = ChangePasswordState()

    private var email: String = ""
    fun changePassword(password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            viewModelScope.launch {
                val response = changePasswordUseCase.changePassword(
                    ChangePasswordUseCase.Parameters(
                        email,
                        password
                    )
                )
                if (response.succeeded) {
                    dispatchEvent(ChangePasswordEvent.ChangePasswordSuccess)
                } else {
                    dispatchEvent(ChangePasswordEvent.ChangePasswordFailure(response.requireError.message))
                }
            }
        } else {
            dispatchEvent(ChangePasswordEvent.ChangePasswordFailure("Password not match"))
        }
    }

    fun setEmail(email: String) {
        this.email = email
    }
}