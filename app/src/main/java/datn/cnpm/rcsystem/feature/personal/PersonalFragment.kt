package datn.cnpm.rcsystem.feature.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentPersonalBinding

/**
 * [PersonalFragment]
 */
@AndroidEntryPoint
class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalBinding
        get() = FragmentPersonalBinding::inflate

    private val viewModel: PersonalViewModel by viewModels()
    override fun initData(data: Bundle?) {

    }

    override fun initViews() {
        SingletonObject.customer?.let {
            GlideHelper.loadImage(
                it.avatar.orEmpty(),
                binding.ivAvatar,
                R.drawable.ic_person
            )
            binding.tvWeight.text = "${it.garbage?.exchange}"
            binding.tvName.text = it.name
            binding.tvPoint.text = it.point?.remainPoint.toPoint()
        }
    }

    override fun initActions() {
        binding.apply {
            tvUpdateInfo.setOnClickListener {
                findNavController().navigate(R.id.updateAccountInfoFragment)
            }

            ivBack.setOnClickListener { findNavController().popBackStack() }
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
        }
    }
}