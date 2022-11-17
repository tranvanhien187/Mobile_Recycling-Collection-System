package datn.cnpm.rcsystem.feature.personal

import android.content.Intent
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
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

/**
 * [PersonalFragment]
 */
@AndroidEntryPoint
class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalBinding
        get() = FragmentPersonalBinding::inflate

    private val viewModel: PersonalViewModel by viewModels()

    @Inject
    lateinit var authPreference: AuthPreference
    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) = Unit

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
            llUpdateInfo.setOnClickListener {
                findNavController().navigate(R.id.updateAccountInfoFragment)
            }
            llLogout.setOnClickListener {
                val intent = Intent(this@PersonalFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.isRememberMe = false
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