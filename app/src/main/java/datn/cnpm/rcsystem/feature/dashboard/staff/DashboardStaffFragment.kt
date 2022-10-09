package datn.cnpm.rcsystem.feature.dashboard.staff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.basesource.common.utils.glide.GlideHelper
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentStaffDashboardBinding

/**
 * [DashboardStaffFragment]
 */
@AndroidEntryPoint
class DashboardStaffFragment : BaseFragment<FragmentStaffDashboardBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStaffDashboardBinding
        get() = FragmentStaffDashboardBinding::inflate

    private val viewModel: DashboardStaffViewModel by viewModels()
    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {

        binding.apply {
            ivForm.setOnClickListener {
                findNavController().navigate(R.id.transportFormFragment)
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
                selector = { state -> state.userEntity },
                observer = { user ->
                    user?.let {
                        GlideHelper.loadImage(
                            user.avatar.orEmpty(),
                            binding.ivAvatar,
                            R.drawable.ic_person
                        )

                        binding.tvGarbageWeight.text = getString(
                            R.string.label_total_weight,
                            it.garbage?.exchange ?: 0.0
                        )
                        binding.tvUsername.text = it.name
                    }

                }
            )
        }
    }
}