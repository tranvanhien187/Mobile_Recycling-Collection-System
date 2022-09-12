package datn.cnpm.rcsystem.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.databinding.FragmentLoginBinding

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
//                viewModel.login(tieUsername.text.toString(), tieUsername.text.toString())
                findNavController().navigate(R.id.registerFragment)
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
//                is LoginEvent.LoginSuccess -> {
//                    event.user?.let { navigateToHomeScreen(it) }
//                }
//                is LoginEvent.LoginFailed -> {
//                }
//
//                is LoginEvent.ErrorEmail -> {
//                    binding.tilEmail.error = event.error
//                }
//
//                is LoginEvent.ErrorPassword -> {
//                    binding.tilPassword.error = event.error
//                }
                is LoginEvent.UserLoginSuccess -> {
//                    val intent = Intent(this.activity, UserActivity::class.java)
//                    activity?.startActivity(intent)
                }
                is LoginEvent.DealerLoginSuccess -> {
//                    val intent = Intent(this.activity, DealerActivity::class.java)
//                    activity?.startActivity(intent)
                }
                else -> {}

            }
        }
    }

}