package datn.cnpm.rcsystem.feature.transportform.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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


    private var transportAdapter: TransportFormScreenAdapter? = null
    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        showToolbar(getString(R.string.receive_form_label), R.drawable.ic_back)
        transportAdapter = TransportFormScreenAdapter(this)
        binding.apply {
            pager.adapter = transportAdapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                when (position) {
                    HistoryTab.Gift.ordinal -> tab.text = getString(R.string.gift_label)
                    HistoryTab.Garbage.ordinal -> tab.text = getString(R.string.garbage_label)
                }
            }.attach()
            tabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.position.let {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        transportAdapter = null
    }
}

enum class HistoryTab {
    Gift,
    Garbage
}