package datn.cnpm.rcsystem.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.databinding.FragmentLoginBinding
import datn.cnpm.rcsystem.feature.home.staff.HomeStaffActivity
import datn.cnpm.rcsystem.feature.home.customer.HomeCustomerActivity
import datn.cnpm.rcsystem.feature.updateaccountifo.UpdateCustomerInfoFragment

/**
 * A simple [LoginFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel: LoginViewModel by viewModels()

    override fun initData(data: Bundle?) = Unit

    override fun initViews() = Unit

    override fun isDisableFullScreen(): Boolean = false

    override fun initActions() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.loginByPass(
                    tieUsername.text.toString().trim(),
                    tiePassword.text.toString().trim(),
                    cbRememberMe.isChecked
                )
            }
            tvForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.forgotPasswordFragment)
            }

            tvRegister.createSpannableString(
                startIndex = 19,
                endIndex = 40,
                isBoldClickableContent = true, click = {
                    findNavController().navigate(R.id.registerFragment)
                })
        }
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
                is LoginEvent.ValidateField -> {
                    binding.apply {
                        tieUsername.error =
                            if (event.errorUsername == null) null else event.errorUsername.value
                        tilPassword.error =
                            if (event.errorPassword == null) null else event.errorPassword.value
                    }
                }
                is LoginEvent.CustomerLoginSuccess -> {
                    val intent = Intent(this.activity, HomeCustomerActivity::class.java)
                    activity?.startActivity(intent)
                }
                is LoginEvent.LoginFailure -> {
                    showError(event.message)
                }
                is LoginEvent.CustomerUpdatedYet -> {
                    findNavController().navigate(
                        R.id.updateAccountInfoFragment,
                        bundleOf(
                            Pair(UpdateCustomerInfoFragment.IS_UPDATED_KEY, false),
                            Pair(UpdateCustomerInfoFragment.ID_KEY, event.uuid)
                        )
                    )
                }

                is LoginEvent.StaffLoginSuccess -> {
                    val intent = Intent(this.activity, HomeStaffActivity::class.java)
                    activity?.startActivity(intent)
                }
                else -> {}

            }
        }
    }
}