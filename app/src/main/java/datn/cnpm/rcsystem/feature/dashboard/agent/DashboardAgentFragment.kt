package datn.cnpm.rcsystem.feature.dashboard.agent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentAgentDashboardBinding
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject


@AndroidEntryPoint
class DashboardAgentFragment : BaseFragment<FragmentAgentDashboardBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAgentDashboardBinding
        get() = FragmentAgentDashboardBinding::inflate

    private val viewModel: DashboardAgentViewModel by viewModels()

    @Inject
    lateinit var authPreference: AuthPreference

    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) {
        viewModel.getAgentInfo()
    }

    override fun initViews() {
        binding.apply {
            ivMyTPlace.setOnClickListener {
                findNavController().navigate(R.id.myTradingPlaceFragment)
            }

            iVMyGift.setOnClickListener {
                findNavController().navigate(R.id.myGiftFragment)
            }

            btnSetting.setOnClickListener {
                val intent =
                    Intent(this@DashboardAgentFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.isRememberMe = false
            }
        }
    }

    override fun initActions() {
    }

    override fun initObservers() {
    }
}