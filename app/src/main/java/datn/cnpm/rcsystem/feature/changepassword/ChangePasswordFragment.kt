package datn.cnpm.rcsystem.feature.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentChangePasswordBinding
import datn.cnpm.rcsystem.feature.otp.OTPFragment

/**
 * [ChangePasswordFragment]
 */
@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChangePasswordBinding
        get() = FragmentChangePasswordBinding::inflate

    private val viewModel: ChangePasswordViewModel by viewModels()
    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.setEmail(it.getString(OTPFragment.EMAIL_KEY, ""))
        }
    }

    override fun initViews() {
        binding.apply {
            btnSend.setOnClickListener {
                viewModel.changePassword(
                    tiePassword.text.toString(),
                    tieReTypePassword.text.toString()
                )
            }
        }
    }

    override fun initActions() {
    }

    override fun initObservers() {
        viewModel.observe(
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

        viewModel.liveEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ChangePasswordEvent.ChangePasswordFailure -> {
                    showError(event.message)
                }
                is ChangePasswordEvent.ChangePasswordSuccess -> {
                    findNavController().apply {
                        navigate(
                            R.id.loginFragment
                        )
                        backQueue.clear()
                    }
                }
            }
        }
    }
}