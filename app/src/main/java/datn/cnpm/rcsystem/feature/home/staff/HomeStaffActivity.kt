package datn.cnpm.rcsystem.feature.home.staff

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.ActivityHomeStaffBinding
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

@AndroidEntryPoint
class HomeStaffActivity : BaseActivity<ActivityHomeStaffBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeStaffBinding
        get() = ActivityHomeStaffBinding::inflate

    @Inject
    lateinit var authPreference: AuthPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}