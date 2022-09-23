package datn.cnpm.rcsystem.feature.history.garbage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGarbageHistoryBinding
import datn.cnpm.rcsystem.feature.history.HistoryAdapter

/**
 * A simple [GarbageHistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GarbageHistoryFragment : BaseFragment<FragmentGarbageHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGarbageHistoryBinding
        get() = FragmentGarbageHistoryBinding::inflate

    private val viewModel: GarbageHistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter
    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
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
    }
}
