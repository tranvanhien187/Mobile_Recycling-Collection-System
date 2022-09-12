package datn.cnpm.rcsystem.feature.loading

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentLoadingBinding
import datn.cnpm.rcsystem.feature.home.HomeActivity
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoadingFragment: BaseFragment<FragmentLoadingBinding>() {

    @Inject
    lateinit var authPreference: AuthPreference

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoadingBinding
        get() = FragmentLoadingBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        lifecycleScope.launch {
            delay(1000)
            if (authPreference.role.isNotEmpty()) {
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                intent.putExtra("ROLE",authPreference.role)
                startActivity(intent)
            } else {
                findNavController().navigate(R.id.loginFragment)
            }
        }

    }

    override fun initActions() {
    }

    override fun initObservers() {
    }
}