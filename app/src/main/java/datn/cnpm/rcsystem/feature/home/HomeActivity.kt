package datn.cnpm.rcsystem.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.databinding.ActivityHomeBinding
import datn.cnpm.rcsystem.data.entitiy.Role

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myNavHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater

        val intent = intent
        when (intent.getStringExtra("ROLE")) {
            Role.USER.toString() -> {
                val graph = inflater.inflate(R.navigation.nav_user)
                myNavHostFragment.navController.graph = graph
            }
            Role.DEALER.toString() -> {
                val graph = inflater.inflate(R.navigation.nav_dealer)
                myNavHostFragment.navController.graph = graph
            }
            else -> {}
        }


    }
}
