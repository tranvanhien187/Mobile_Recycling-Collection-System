package datn.cnpm.rcsystem.feature.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentOtpBinding

/**
 * [OTPFragment]
 */
@AndroidEntryPoint
class OTPFragment : BaseFragment<FragmentOtpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate

    private val viewModel: OTPViewModel by viewModels()
    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.setData(data.getString(EMAIL_KEY, ""), data.getInt(TYPE_KEY))
        }
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.apply {
            btnSend.setOnClickListener {
                viewModel.validateOTP(ifOTPInput.getCurrentInput())
            }
            tvReSendOTP.setOnClickListener {
                viewModel.genOTP()
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
                    is OTPEvent.ValidateOTPSuccess -> {
                        findNavController().navigate(R.id.changePasswordFragment)
                    }
                    is OTPEvent.ValidateOTPFailure -> {

                    }
                    is OTPEvent.ReGenOTPSuccess-> {
                        Toast.makeText(requireContext(),"New otp has been sent to your email!", Toast.LENGTH_LONG).show()
                    }
                    is OTPEvent.ReGenOTPFailure -> {

                    }
                    else -> {}

                }
            }

        }
    }

    companion object {
        const val EMAIL_KEY = "EMAIL_KEY"
        const val TYPE_KEY = "TYPE_KEY"
    }
}