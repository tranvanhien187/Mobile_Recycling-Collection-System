package datn.cnpm.rcsystem.feature.history

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
class HistoryViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    BaseViewModel<HistoryState, HistoryEvent>() {
    override fun initState() = HistoryState()
}