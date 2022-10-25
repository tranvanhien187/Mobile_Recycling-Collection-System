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
        authPreference.role = Role.STAFF.name
        authPreference.uuid = "b45b42d3cd58adfa969c13af56e3852185b5b47666ef79e469ad6a33b0ba300b"
        authPreference.accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4ZTVlMzI2MGNlYThlOGJkNzFkYjNjNzhmY2I1NmUxMGJjMGEzZDJjZGM2NjhmNmU0ZTRlYjI3NzA0MGFkOTMzLERhcmtOaWdodCIsImlzcyI6ImFkbWluSGllblRWNiIsImlhdCI6MTY2Mzg1MzIyOH0.XT11gdcfH6jX_AkfEGw1omhrkFPw1FruTOv-xFuEcPTuyogeomzLGhwwGs4li0HLYQSkusKm9dRUF7vQfqRSkA"
    }
}