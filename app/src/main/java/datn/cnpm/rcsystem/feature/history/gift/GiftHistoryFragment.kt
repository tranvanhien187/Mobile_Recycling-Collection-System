package datn.cnpm.rcsystem.feature.history.gift

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
import datn.cnpm.rcsystem.databinding.FragmentGiftHistoryBinding
import datn.cnpm.rcsystem.feature.history.HistoryItemAdapter
import datn.cnpm.rcsystem.feature.history.garbage.GarbageHistoryFragment

/**
 * A simple [GiftHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GiftHistoryFragment : BaseFragment<FragmentGiftHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftHistoryBinding
        get() = FragmentGiftHistoryBinding::inflate

    private val viewModel: GiftHistoryViewModel by viewModels()
    private lateinit var giftAdapter: HistoryItemAdapter

    override fun initData(data: Bundle?) {
        viewModel.fetchGiftHistory()
    }

    override fun initViews() {
        giftAdapter = HistoryItemAdapter()
        binding.rvGiftHistory.adapter = giftAdapter
    }

    override fun initActions() {
        giftAdapter.onItemClick = { gift ->
//            findNavController().navigate(
//                R.id.giftHistoryDetailFragment,
//                bundleOf(Pair(GIFT_HISTORY_KEY, gift))
//            )
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
            selector = { state -> state.giftHistoryList },
            observer = { giftList ->
                giftList?.let {
                    giftAdapter.submitList(it)
                }
            }
        )
    }

    companion object {
        const val GIFT_HISTORY_KEY = "GIFT_HISTORY_KEY"
    }
}