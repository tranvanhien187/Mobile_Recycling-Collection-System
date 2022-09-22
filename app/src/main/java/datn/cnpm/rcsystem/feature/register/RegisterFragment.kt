package datn.cnpm.rcsystem.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.databinding.FragmentRegisterBinding
import datn.cnpm.rcsystem.feature.updateaccountifo.UpdateAccountInfoFragment

/**
 * [RegisterFragment]
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    private val viewModel: RegisterViewModel by viewModels()
    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.apply {
            tvLogin.createSpannableString(
                22,
                27,
                true
            ) {
                findNavController().navigate(R.id.loginFragment)
            }
            btnRegister.setOnClickListener {
                viewModel.register(
                    tieEmail.text.toString(),
                    tieUsername.text.toString(),
                    tiePassword.text.toString(),
                    tieReTypePassword.text.toString()
                )
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
                is RegisterEvent.ValidateField -> {
                    binding.apply {
                        tilEmail.error =
                            if (event.errorEmail == null) null else event.errorEmail.value
                        tilUsername.error =
                            if (event.errorUsername == null) null else event.errorUsername.value
                        tilPassword.error =
                            if (event.errorPassword == null) null else event.errorPassword.value
                        tilReTypePassword.error =
                            if (event.errorRetypePassword == null) null else event.errorRetypePassword.value
                    }
                }
                is RegisterEvent.RegisterSuccess -> {
                    findNavController().navigate(
                        R.id.updateAccountInfoFragment,
                        bundleOf(
                            Pair(UpdateAccountInfoFragment.IS_UPDATED_KEY, false),
                            Pair(UpdateAccountInfoFragment.UUID_KEY, event.uuid)
                        )
                    )
                }
                is RegisterEvent.RegisterFailure -> {
                    showError(event.error)
                }
                else -> {}

            }
        }
    }
}