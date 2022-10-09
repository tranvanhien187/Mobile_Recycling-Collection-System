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
import datn.cnpm.rcsystem.feature.home.user.HomeUserActivity
import datn.cnpm.rcsystem.feature.updateaccountifo.UpdateAccountInfoFragment

/**
 * A simple [LoginFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel: LoginViewModel by viewModels()

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login(
                    tieUsername.text.toString(),
                    tiePassword.text.toString(),
                    cbRememberMe.isChecked
                )
            }
            tvForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.forgotPasswordFragment)
            }

            tvRegister.createSpannableString(
                19,
                40,
                true
            ) {
                findNavController().navigate(R.id.registerFragment)
            }
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
                is LoginEvent.UserLoginSuccess -> {
                    val intent = Intent(this.activity, HomeUserActivity::class.java)
                    activity?.startActivity(intent)
                }
                is LoginEvent.LoginFailure -> {
                    showError(event.message)
                }
                is LoginEvent.UserUpdatedYet -> {
                    findNavController().navigate(
                        R.id.updateAccountInfoFragment,
                        bundleOf(
                            Pair(UpdateAccountInfoFragment.IS_UPDATED_KEY, false),
                            Pair(UpdateAccountInfoFragment.UUID_KEY, event.uuid)
                        )
                    )
                }
                is LoginEvent.AgentLoginSuccess -> {
                    val intent = Intent(this.activity, HomeUserActivity::class.java)
                    activity?.startActivity(intent)
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