package datn.cnpm.rcsystem.feature.transportform.list

import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TransportFormViewModel @Inject constructor() :
    BaseViewModel<TransportFormState, TransportFormEvent>() {
    override fun initState() = TransportFormState()
}