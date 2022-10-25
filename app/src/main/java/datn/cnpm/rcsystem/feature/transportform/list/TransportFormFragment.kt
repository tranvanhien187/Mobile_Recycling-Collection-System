package datn.cnpm.rcsystem.feature.transportform.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentTransportFormBinding

/**
 * [TransportFormFragment]
 */
@AndroidEntryPoint
class TransportFormFragment : BaseFragment<FragmentTransportFormBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransportFormBinding
        get() = FragmentTransportFormBinding::inflate


    private val viewModel: TransportFormViewModel by viewModels()

    private val formAdapter: TransportFormAdapter by lazy { TransportFormAdapter() }


    override fun initData(data: Bundle?) {
        viewModel.listener()
    }

    override fun initViews() {
        showToolbar(getString(R.string.transport_form_label), R.drawable.ic_back)
        binding.rvForm.adapter = formAdapter
    }

    override fun initActions() {
        formAdapter.onClickItem = { data ->
            findNavController().navigate(
                R.id.receiveFormFragment, bundleOf(
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