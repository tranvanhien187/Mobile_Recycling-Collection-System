package datn.cnpm.rcsystem.feature.history.garbage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGarbageHistoryBinding

/**
 * A simple [GarbageHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GarbageHistoryFragment : BaseFragment<FragmentGarbageHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGarbageHistoryBinding
        get() = FragmentGarbageHistoryBinding::inflate

    private val viewModel: GarbageHistoryViewModel by viewModels()
    private lateinit var garbageAdapter: GarbageHistoryAdapter
    override fun initData(data: Bundle?) {
        viewModel.fetchGiftHistory()
    }

    override fun initViews() {
        garbageAdapter = GarbageHistoryAdapter()
        binding.rvGarbageHistory.adapter = garbageAdapter
    }

    override fun initActions() {
        garbageAdapter.onItemClick = { garbage ->
            findNavController().navigate(
                R.id.garbageHistoryDetailFragment,
                bundleOf(Pair(GARBAGE_HISTORY_KEY, garbage))
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
            selector = { state -> state.garbageList },
            observer = { giftList ->
                giftList?.let {
                    garbageAdapter.submitList(it)
                }
            }
        )
    }

    companion object {
        const val GARBAGE_HISTORY_KEY = "GARBAGE_HISTORY_KEY"
    }
}
