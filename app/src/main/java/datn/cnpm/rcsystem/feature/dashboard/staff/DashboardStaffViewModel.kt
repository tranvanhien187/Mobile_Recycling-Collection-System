package datn.cnpm.rcsystem.feature.dashboard.staff

import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardStaffViewModel @Inject constructor(
) :
    BaseViewModel<DashboardStaffState, DashboardStaffEvent>() {
    override fun initState() = DashboardStaffState()

}