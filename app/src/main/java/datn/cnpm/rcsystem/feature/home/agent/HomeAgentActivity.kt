package datn.cnpm.rcsystem.feature.home.agent

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.ActivityHomeDealerBinding
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

@AndroidEntryPoint
class HomeAgentActivity : BaseActivity<ActivityHomeDealerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeDealerBinding
        get() = ActivityHomeDealerBinding::inflate

    @Inject
    lateinit var authPreference: AuthPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authPreference.role = Role.AGENT.name
        authPreference.uuid = "ef5143a2551ff782569090fcf72206a1d393ebc523a892b3142f0729bc5f6985"
    }
}