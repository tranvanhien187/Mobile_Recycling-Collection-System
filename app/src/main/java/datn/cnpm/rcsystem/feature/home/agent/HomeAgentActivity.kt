package datn.cnpm.rcsystem.feature.home.agent

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.ActivityHomeAgentBinding
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

@AndroidEntryPoint
class HomeAgentActivity : BaseActivity<ActivityHomeAgentBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeAgentBinding
        get() = ActivityHomeAgentBinding::inflate
}