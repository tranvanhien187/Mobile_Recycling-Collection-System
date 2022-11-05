package datn.cnpm.rcsystem.feature.history.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentListBinding
import datn.cnpm.rcsystem.feature.history.HistoryItemAdapter

/**
 * A simple [GiftHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GiftHistoryFragment : BaseFragment<FragmentListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding
        get() = FragmentListBinding::inflate

    private val viewModel: GiftHistoryViewModel by viewModels()
    private lateinit var giftAdapter: HistoryItemAdapter

    override fun initData(data: Bundle?) {
        viewModel.fetchGiftHistory()
    }

    override fun initViews() {
        giftAdapter = HistoryItemAdapter()
        binding.rvData.adapter = giftAdapter
    }

    override fun initActions() {
        giftAdapter.onItemClick = { gift ->
            findNavController().navigate(
                R.id.giftHistoryDetailFragment,
                bundleOf(Pair(GIFT_HISTORY_ID_KEY, gift.id))
            )
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
        const val GIFT_HISTORY_ID_KEY = "GIFT_HISTORY_ID_KEY"
    }
}