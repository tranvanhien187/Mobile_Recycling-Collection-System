package datn.cnpm.rcsystem.feature.personal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.FragmentPersonalBinding
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.feature.otp.OTPFragment
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
        if (authPreference.role == Role.STAFF.name) {
            SingletonObject.staff?.let {
                GlideHelper.loadImage(
                    it.avatar,
                    binding.ivAvatar,
                    R.drawable.ic_person
                )
                binding.tvWeight.text = "${it.weightTotal}"
                binding.tvName.text = it.name
                binding.tvLabel.text = "Number of gift"
                binding.tvPoint.text = it.giftCount.toString()
                binding.tvInformation.text = "Your information"
            }
        } else if (authPreference.role == Role.CUSTOMER.name) {
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
    }

    override fun initActions() {
        binding.apply {
            llUpdateInfo.setOnClickListener {
                findNavController().navigate(R.id.updateAccountInfoFragment)
            }
            llResetPassword.setOnClickListener {
                if (authPreference.role == Role.STAFF.name) {

                    SingletonObject.staff?.let {
                        findNavController().navigate(
                            R.id.changePasswordFragment,
                            bundleOf(Pair(OTPFragment.EMAIL_KEY, it.email))
                        )
                    }
                } else if (authPreference.role == Role.CUSTOMER.name) {
                    SingletonObject.customer?.let {
                        findNavController().navigate(
                            R.id.changePasswordFragment,
                            bundleOf(Pair(OTPFragment.EMAIL_KEY, it.email))
                        )
                    }
                }
            }
            llLogout.setOnClickListener {
                val intent =
                    Intent(this@PersonalFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.id = ""
                authPreference.accessToken = ""
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