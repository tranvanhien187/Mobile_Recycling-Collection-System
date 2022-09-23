package datn.cnpm.rcsystem.feature.history.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGiftHistoryBinding

/**
 * A simple [GiftHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GiftHistoryFragment : BaseFragment<FragmentGiftHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftHistoryBinding
        get() = FragmentGiftHistoryBinding::inflate

    private val viewModel: GiftHistoryViewModel by viewModels()
    private lateinit var giftAdapter: GiftAdapter

    override fun initData(data: Bundle?) {
        viewModel.fetchGiftHistory()
    }

    override fun initViews() {
        giftAdapter = GiftAdapter()
        binding.rvGiftHistory.adapter = giftAdapter
    }

    override fun initActions() {
        giftAdapter.onItemClick = { gift ->
            Toast.makeText(requireContext(), gift.name.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.apply {
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
            selector = { state -> state.giftList },
            observer = { giftList ->
                giftList?.let {
                    giftAdapter.submitList(it)
                }
            }
        )
    }
}