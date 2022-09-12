package datn.cnpm.rcsystem.feature.otp

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.OTPType
import datn.cnpm.rcsystem.domain.usecase.GenOTPUseCase
import datn.cnpm.rcsystem.domain.usecase.ValidateOTPUseCase
import datn.cnpm.rcsystem.feature.forgotpassword.ForgotPasswordEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(
    private val validateOTPUseCase: ValidateOTPUseCase,
    private val genOTPUseCase: GenOTPUseCase
) :
    BaseViewModel<OTPState, OTPEvent>() {
    override fun initState() = OTPState()

    private var email: String = ""
    private var type: Int = 0

    fun setData(email: String, type: Int) {
        this.email = email
        this.type = type
    }

    fun validateOTP(otp: String) {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val params = ValidateOTPUseCase.Parameters(email, otp)
            val result = validateOTPUseCase.validateOTP(params)
            if (result.succeeded) {
                dispatchEvent(OTPEvent.ValidateOTPSuccess)
            } else {
                dispatchEvent(OTPEvent.ValidateOTPFailure)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }

    fun genOTP() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val params = GenOTPUseCase.Parameters(email, OTPType.EMAIL)
            val result = genOTPUseCase.genOTP(params)
            if (result.succeeded) {
                dispatchEvent(OTPEvent.ReGenOTPSuccess)
            } else {
                dispatchEvent(OTPEvent.ReGenOTPFailure)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }
}