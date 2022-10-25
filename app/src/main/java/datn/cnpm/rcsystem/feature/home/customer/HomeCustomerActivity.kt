package datn.cnpm.rcsystem.feature.home.customer

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.databinding.ActivityHomeCustomerBinding
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

@AndroidEntryPoint
class HomeCustomerActivity : BaseActivity<ActivityHomeCustomerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeCustomerBinding
        get() = ActivityHomeCustomerBinding::inflate

    @Inject
    lateinit var authPreference: AuthPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
