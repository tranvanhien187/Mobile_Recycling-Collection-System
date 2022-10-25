package datn.cnpm.rcsystem.feature.home.customer

import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeCustomerViewModel @Inject constructor() :
    BaseViewModel<HomeCustomerState, HomeCustomerEvent>() {
    override fun initState() = HomeCustomerState()
}