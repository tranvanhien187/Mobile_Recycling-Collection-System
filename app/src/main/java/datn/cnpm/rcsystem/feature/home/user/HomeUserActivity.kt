package datn.cnpm.rcsystem.feature.home.user

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.ActivityHomeUserBinding
import datn.cnpm.rcsystem.feature.dashboard.DashboardFragment
import datn.cnpm.rcsystem.feature.tradingplace.TradingPlaceFragment

@AndroidEntryPoint
class HomeUserActivity : BaseActivity<ActivityHomeUserBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeUserBinding
        get() = ActivityHomeUserBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    navHostFragment.navController.navigate(R.id.dashboardFragment)
                }
                R.id.menu_history -> {
                    navHostFragment.navController.navigate(R.id.historyFragment)
                }
                R.id.menu_place -> {
                    navHostFragment.navController.navigate(R.id.tradingPlaceFragment)
                }
                R.id.menu_personal -> {}
            }
            true
        }

    }
}
