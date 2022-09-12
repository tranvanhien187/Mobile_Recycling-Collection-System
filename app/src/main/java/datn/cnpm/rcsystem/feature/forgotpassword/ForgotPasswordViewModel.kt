package datn.cnpm.rcsystem.feature.forgotpassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.OTPType
import datn.cnpm.rcsystem.domain.usecase.GenOTPUseCase
import datn.cnpm.rcsystem.domain.usecase.RegisterUseCase
import datn.cnpm.rcsystem.feature.changepassword.ChangePasswordEvent
import datn.cnpm.rcsystem.feature.changepassword.ChangePasswordState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val genOTPUseCase: GenOTPUseCase) :
    BaseViewModel<ForgotPasswordState, ForgotPasswordEvent>() {
    override fun initState() = ForgotPasswordState()

    fun genOTP(email: String) {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val params = GenOTPUseCase.Parameters(email, OTPType.EMAIL)
            val result = genOTPUseCase.genOTP(params)
            if(result.succeeded) {
                dispatchEvent(ForgotPasswordEvent.GenOTPSuccess)
            } else {
                dispatchEvent(ForgotPasswordEvent.GenOTPFailure)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}