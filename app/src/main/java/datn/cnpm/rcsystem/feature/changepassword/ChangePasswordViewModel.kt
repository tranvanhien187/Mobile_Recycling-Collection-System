package datn.cnpm.rcsystem.feature.changepassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    BaseViewModel<ChangePasswordState, ChangePasswordEvent>() {
    override fun initState() = ChangePasswordState()

    fun register() {
        viewModelScope.launch {

        }
    }
}