package datn.cnpm.rcsystem.feature.authentication

import android.view.LayoutInflater
import datn.cnpm.rcsystem.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.databinding.ActivityAuthenticationBinding

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityAuthenticationBinding
        get() = ActivityAuthenticationBinding::inflate
}