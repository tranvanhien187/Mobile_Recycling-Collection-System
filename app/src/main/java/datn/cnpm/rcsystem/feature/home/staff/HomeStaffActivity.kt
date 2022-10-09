package datn.cnpm.rcsystem.feature.home.staff

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.databinding.ActivityHomeStaffBinding

@AndroidEntryPoint
class HomeStaffActivity : BaseActivity<ActivityHomeStaffBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeStaffBinding
        get() = ActivityHomeStaffBinding::inflate
}