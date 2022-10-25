package datn.cnpm.rcsystem.feature.home.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.data.entitiy.Role
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
        authPreference.role = Role.CUSTOMER.name
        authPreference.uuid = "23827428501f9a7ee63564ceb12621af69b202e84e0968bef71b6ce0a5106db4"
        authPreference.accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4ZTVlMzI2MGNlYThlOGJkNzFkYjNjNzhmY2I1NmUxMGJjMGEzZDJjZGM2NjhmNmU0ZTRlYjI3NzA0MGFkOTMzLERhcmtOaWdodCIsImlzcyI6ImFkbWluSGllblRWNiIsImlhdCI6MTY2Mzg1MzIyOH0.XT11gdcfH6jX_AkfEGw1omhrkFPw1FruTOv-xFuEcPTuyogeomzLGhwwGs4li0HLYQSkusKm9dRUF7vQfqRSkA"
    }
}
