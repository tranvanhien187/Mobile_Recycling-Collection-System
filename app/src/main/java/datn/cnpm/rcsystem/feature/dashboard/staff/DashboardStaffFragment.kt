package datn.cnpm.rcsystem.feature.dashboard.staff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentStaffDashboardBinding
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

/**
 * [DashboardStaffFragment]
 */
@AndroidEntryPoint
class DashboardStaffFragment : BaseFragment<FragmentStaffDashboardBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStaffDashboardBinding
        get() = FragmentStaffDashboardBinding::inflate

    @Inject
    lateinit var authPreference: AuthPreference
    private val viewModel: DashboardStaffViewModel by viewModels()
    override fun initData(data: Bundle?) {
        viewModel.fetchStaffInfo()
    }

    override fun initViews() {
    }

    override fun initActions() {

        binding.apply {
            ivForm.setOnClickListener {
                findNavController().navigate(R.id.transportFormFragment)
            }
            ivHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            ivTrophy.setOnClickListener {
                findNavController().navigate(R.id.leaderboardFragment)
            }

            btnSetting.setOnClickListener {
                val intent =
                    Intent(this@DashboardStaffFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.isRememberMe = false
            }
        }
    }

    override fun initObservers() {

        viewModel.apply {
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.loading },
                observer = { loading ->
                    if (loading) {
                        showLoading()
                    } else {
                        hideLoading()
                    }
                }
            )
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.staff },
                observer = { staff ->
                    staff?.let {
                        GlideHelper.loadImage(
                            staff.avatar,
                            binding.ivAvatar,
                            R.drawable.ic_person
                        )

                        val garbageCount = "${it.weightTotal} kgs"
                        val giftCount = "${it.giftCount} gift"
                        val count = getString(
                            R.string.label_total_weight_and_gift,
                            garbageCount, giftCount
                        )
                        binding.tvGarbageWeight.createSpannableString(
                            startIndex = 16,
                            endIndex = 16 + garbageCount.length,
                            isBoldClickableContent = false,
                            color = R.color.orange_f05a30,
                            content = count
                        )
                        binding.tvGarbageWeight.createSpannableString(
                            startIndex = 33 + garbageCount.length,
                            endIndex = 33 + giftCount.length + garbageCount.length,
                            isBoldClickableContent = false,
                            color = R.color.green_00ad31
                        )
                        binding.tvUsername.text = staff.name
                    }
                }
            )
        }
    }
}