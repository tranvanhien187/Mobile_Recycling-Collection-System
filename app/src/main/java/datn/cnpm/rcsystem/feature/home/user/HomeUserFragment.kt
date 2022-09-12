package datn.cnpm.rcsystem.feature.home.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentHomeUserBinding

@AndroidEntryPoint
class HomeUserFragment : BaseFragment<FragmentHomeUserBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeUserBinding
        get() = FragmentHomeUserBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
    }

    override fun initObservers() {
//        viewModel.observe(
//            owner = viewLifecycleOwner,
//            selector = { state -> state.loading },
//            observer = { loading ->
//                if (loading) {
//                    showLoading()
//                } else {
//                    hideLoading()
//                }
//            }
//        )
    }
}