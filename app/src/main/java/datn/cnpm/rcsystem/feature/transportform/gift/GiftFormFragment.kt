package datn.cnpm.rcsystem.feature.transportform.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentListBinding
import datn.cnpm.rcsystem.feature.transportform.TransportFormAdapter


class GiftFormFragment : BaseFragment<FragmentListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding
        get() = FragmentListBinding::inflate


    private val viewModel: GiftFormViewModel by viewModels()

    private val formAdapter: TransportFormAdapter by lazy { TransportFormAdapter() }


    override fun initData(data: Bundle?) {
        viewModel.listener()
    }

    override fun initViews() {
        binding.rvData.adapter = formAdapter
    }

    override fun initActions() {
        formAdapter.onClickItem = { data ->
            findNavController().navigate(
                R.id.receiveFromGiftFragment, bundleOf(
                    Pair(FORM_KEY, data)
                )
            )
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

        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.listForm },
            observer = { listPlace ->
                if (listPlace.isNotEmpty()) {
                    formAdapter.submitList(listPlace)
                }
            }
        )
    }

    companion object {
        const val FORM_KEY = "FORM_KEY"
    }
}