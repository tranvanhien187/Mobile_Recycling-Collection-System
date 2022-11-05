package datn.cnpm.rcsystem.feature.register

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
import datn.cnpm.rcsystem.databinding.FragmentRegisterBinding
import datn.cnpm.rcsystem.feature.updateaccountifo.UpdateCustomerInfoFragment

/**
 * [RegisterFragment]
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    private val viewModel: RegisterViewModel by viewModels()

    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) = Unit

    override fun initViews() = Unit

    override fun initActions() {
        binding.apply {
            tvLogin.createSpannableString(
                startIndex = 22,
                endIndex = 27,
                isBoldClickableContent = true,
                click = {
                    findNavController().navigate(R.id.loginFragment)
                })
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
                            Pair(UpdateCustomerInfoFragment.IS_UPDATED_KEY, false),
                            Pair(UpdateCustomerInfoFragment.ID_KEY, event.uuid)
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