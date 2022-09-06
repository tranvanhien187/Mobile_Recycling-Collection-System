package datn.cnpm.rcsystem.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
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
        binding.buttonFirst.setOnClickListener {
            viewModel.login()
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

//        liveEvent.observe(viewLifecycleOwner) { event ->
//            when (event) {
//                is LoginEvent.LoginSuccess -> {
//                    event.user?.let { navigateToHomeScreen(it) }
//                }
//                is LoginEvent.LoginFailed -> {
//                    Toast.makeText(requireContext(), event.error, Toast.LENGTH_SHORT).show()
//                }
//
//                is LoginEvent.ErrorEmail -> {
//                    binding.tilEmail.error = event.error
//                }
//
//                is LoginEvent.ErrorPassword -> {
//                    binding.tilPassword.error = event.error
//                }
//
//                else -> {}
//
//            }
//        }
    }

}