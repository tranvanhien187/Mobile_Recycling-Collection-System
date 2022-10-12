package datn.cnpm.rcsystem.feature.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentHistoryBinding

/**
 * A simple [HistoryFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

    private val viewModel: HistoryViewModel by viewModels()
    private var historyAdapter: HistoryScreenAdapter? = null
    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        showToolbar(getString(R.string.history_label), R.drawable.ic_back)
        historyAdapter = HistoryScreenAdapter(this)
        binding.apply {
            pager.isSaveEnabled = false
            pager.adapter = historyAdapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                when (position) {
                    HistoryTab.Gift.ordinal -> tab.text = getString(R.string.gift_label)
                    HistoryTab.Garbage.ordinal -> tab.text = getString(R.string.garbage_label)
                }
            }.attach()
            tabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.position.let { position ->
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            })
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        historyAdapter = null
    }
}

enum class HistoryTab {
    Gift,
    Garbage
}