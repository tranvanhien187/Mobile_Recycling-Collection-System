package datn.cnpm.rcsystem.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.databinding.FragmentRegisterBinding

/**
 * [RegisterFragment]
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

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
        }
    }

    override fun initObservers() {
//        showLoading()
//        hideLoading()
    }
}