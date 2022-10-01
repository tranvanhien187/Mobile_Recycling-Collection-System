package datn.cnpm.rcsystem.feature.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentOtpBinding
import datn.cnpm.rcsystem.databinding.FragmentPersonalBinding

/**
 * [PersonalFragment]
 */
@AndroidEntryPoint
class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalBinding
        get() = FragmentPersonalBinding::inflate

    private val viewModel: PersonalViewModel by viewModels()
    override fun initData(data: Bundle?) {

    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.apply {

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


        }
    }
}