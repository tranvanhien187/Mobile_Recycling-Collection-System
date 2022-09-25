package datn.cnpm.rcsystem.feature.history.garbage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGarbageHistoryBinding
import datn.cnpm.rcsystem.feature.history.HistoryAdapter
import datn.cnpm.rcsystem.feature.history.gift.GiftAdapter
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryViewModel

/**
 * A simple [GarbageHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GarbageHistoryFragment : BaseFragment<FragmentGarbageHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGarbageHistoryBinding
        get() = FragmentGarbageHistoryBinding::inflate

    private val viewModel: GarbageHistoryViewModel by viewModels()
    private lateinit var garbageAdapter: GarbageAdapter
    override fun initData(data: Bundle?) {
        viewModel.fetchGiftHistory()
    }

    override fun initViews() {
        garbageAdapter = GarbageAdapter()
        binding.rvGarbageHistory.adapter = garbageAdapter
    }

    override fun initActions() {
        garbageAdapter.onItemClick = { garbage ->
            Toast.makeText(requireContext(), garbage.weight.toString(), Toast.LENGTH_SHORT).show()
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
            selector = { state -> state.garbageList },
            observer = { giftList ->
                giftList?.let {
                    garbageAdapter.submitList(it)
                }
            }
        )
    }
}
