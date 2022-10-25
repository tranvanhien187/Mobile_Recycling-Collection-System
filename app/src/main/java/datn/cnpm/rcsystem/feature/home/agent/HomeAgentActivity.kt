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

    @Inject
    lateinit var authPreference: AuthPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authPreference.role = Role.AGENT.name
        authPreference.uuid = "8331062608f18d9b3a39d41e4752cf4fe2b47606e5170d4351868dd93d31e1bf"
        authPreference.accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4ZTVlMzI2MGNlYThlOGJkNzFkYjNjNzhmY2I1NmUxMGJjMGEzZDJjZGM2NjhmNmU0ZTRlYjI3NzA0MGFkOTMzLERhcmtOaWdodCIsImlzcyI6ImFkbWluSGllblRWNiIsImlhdCI6MTY2Mzg1MzIyOH0.XT11gdcfH6jX_AkfEGw1omhrkFPw1FruTOv-xFuEcPTuyogeomzLGhwwGs4li0HLYQSkusKm9dRUF7vQfqRSkA"
    }
}