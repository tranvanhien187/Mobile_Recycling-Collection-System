package datn.cnpm.rcsystem.feature.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.data.entitiy.OTPType
import datn.cnpm.rcsystem.databinding.FragmentForgotPasswordBinding
import datn.cnpm.rcsystem.feature.otp.OTPFragment.Companion.EMAIL_KEY
import datn.cnpm.rcsystem.feature.otp.OTPFragment.Companion.TYPE_KEY

/**
 * [ForgotPasswordFragment]
 */
@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForgotPasswordBinding
        get() = FragmentForgotPasswordBinding::inflate

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) = Unit

    override fun initViews() = Unit

    override fun initActions() {
        binding.apply {
            btnSend.setOnClickListener {
                viewModel.genOTP(tieEmail.text.toString())
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

            liveEvent.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is ForgotPasswordEvent.GenOTPSuccess -> {
                        findNavController().navigate(
                            R.id.OTPFragment, bundleOf(
                                Pair(EMAIL_KEY, binding.tieEmail.text.toString()),
                                Pair(TYPE_KEY, OTPType.EMAIL.value)
                            )
                        )
                    }
                    is ForgotPasswordEvent.GenOTPFailure -> {

                    }
                    else -> Unit

                }
            }

        }
    }
}