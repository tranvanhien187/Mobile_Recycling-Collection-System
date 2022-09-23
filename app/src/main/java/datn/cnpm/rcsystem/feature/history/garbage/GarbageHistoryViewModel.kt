package datn.cnpm.rcsystem.feature.history.garbage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageHistoryViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    BaseViewModel<GarbageHistoryState, GarbageHistoryEvent>() {
    override fun initState() = GarbageHistoryState()

    fun login(username: String, password: String, isRemember: Boolean) = viewModelScope.launch {
        dispatchState(currentState.copy(loading = true))
        val errorUsername = if (username.length > 6) null else ErrorCode.SHORT_USERNAME
        val errorPassword = if (password.length > 6) null else ErrorCode.SHORT_PASSWORD
        if (errorUsername == null && errorPassword == null) {
            val response =
                loginUseCase.login(LoginUseCase.Parameters(username, password, isRemember))
            if (response.succeeded) {
                when (response.requireData.role) {
                    Role.USER.toString() -> {
                        if (response.requireData.updatedInfo) {
                            dispatchEvent(GarbageHistoryEvent.UserLoginSuccess)
                        } else {
                            dispatchEvent(GarbageHistoryEvent.UserUpdatedYet(response.requireData.uuid))
                        }
                    }
                    Role.DEALER.toString() -> {
                        dispatchEvent(GarbageHistoryEvent.DealerLoginSuccess)
                    }
                    else -> {}
                }

            } else if (response.failed) {
                if (response.requireError.errorCode == ErrorCode.NOT_UPDATE_DEALER) {
                    dispatchEvent(GarbageHistoryEvent.LoginFailure(ErrorCode.NOT_UPDATE_DEALER.value))
                } else {
                    dispatchEvent(GarbageHistoryEvent.LoginFailure(response.requireError.exception.message.toString()))
                }
            } else {
                dispatchEvent(GarbageHistoryEvent.LoginFailure(response.requireError.message))
            }
            dispatchState(currentState.copy(loading = false))
        } else {
            dispatchEvent(GarbageHistoryEvent.ValidateField(errorUsername, errorPassword))
            dispatchState(currentState.copy(loading = false))
        }

    }
}