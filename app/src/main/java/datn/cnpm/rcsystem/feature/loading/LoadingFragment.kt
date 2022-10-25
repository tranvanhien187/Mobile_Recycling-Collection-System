package datn.cnpm.rcsystem.feature.loading

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.FragmentLoadingBinding
import datn.cnpm.rcsystem.feature.home.agent.HomeAgentActivity
import datn.cnpm.rcsystem.feature.home.staff.HomeStaffActivity
import datn.cnpm.rcsystem.feature.home.customer.HomeCustomerActivity
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
            if(authPreference.isRememberMe) {
                if (authPreference.role.isNotEmpty()) {
                    if(authPreference.role == Role.CUSTOMER.toString()) {
                        val intent = Intent(requireActivity(), HomeCustomerActivity::class.java)
                        startActivity(intent)
                    } else if(authPreference.role == Role.AGENT.toString()) {
                        val intent = Intent(requireActivity(), HomeAgentActivity::class.java)
                        startActivity(intent)
                    } else if(authPreference.role == Role.STAFF.toString()) {
                        val intent = Intent(requireActivity(), HomeStaffActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToLoginFragment())
                }
            } else {
                findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToLoginFragment())
            }
        }

    }

    override fun initActions() {
    }

    override fun initObservers() {
    }
}