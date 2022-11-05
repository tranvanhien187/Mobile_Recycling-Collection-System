package datn.cnpm.rcsystem.feature.gift.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.ArrayRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGiftBinding
import datn.cnpm.rcsystem.feature.gift.detail.GiftDetailFragment.Companion.GIFT_ID_KEY

/**
 * [GiftFragment]
 */
@AndroidEntryPoint
class GiftFragment : BaseFragment<FragmentGiftBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftBinding
        get() = FragmentGiftBinding::inflate

    companion object {
        private const val TAB_RANK_POSITION = 0
        private const val TAB_CITY_POSITION = 1
        private const val TAB_DISTRICT_POSITION = 2
    }

    private val viewModel: GiftViewModel by viewModels()

    private val giftAdapter: GiftAdapter by lazy { GiftAdapter() }

    private var rankPosition = 0
    private var cityPosition = 0
    private var districtPosition = 0

    override fun initData(data: Bundle?) {
        viewModel.fetchTPlaceForUser()
    }

    override fun initViews() {
        showToolbar(getString(R.string.trading_place_label), R.drawable.ic_back)
        binding.rvGift.adapter = giftAdapter

        applyDataSpinner(R.array.level, rankPosition) {
            rankPosition = it
        }
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.tabLayoutFilter.run {
            resources.getStringArray(R.array.filter).toList().forEach {
                addTab(newTab().setText(it))
            }

            for (i in 0 until tabCount) {
                val tabview = getTabAt(i)?.view
                if (tabview != null) {
                    when (i) {
                        TAB_RANK_POSITION -> {
                            tabview.background =
                                ContextCompat.getDrawable(context, R.drawable.bg_tab_first_item)
                        }
                        TAB_DISTRICT_POSITION -> {
                            tabview.background =
                                ContextCompat.getDrawable(context, R.drawable.bg_tab_last_item)
                        }
                        else -> {
                            tabview.background =
                                ContextCompat.getDrawable(context, R.drawable.bg_tab_mid_item)
                        }
                    }
                }
            }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.position) {
                        TAB_RANK_POSITION -> {
                            applyDataSpinner(R.array.level, rankPosition) {
                                rankPosition = it
                            }
                        }
                        TAB_CITY_POSITION -> {
                            applyDataSpinner(R.array.City, cityPosition) {
                                if (cityPosition != it) {
                                    districtPosition = 0
                                }
                                cityPosition = it
                            }
                        }
                        TAB_DISTRICT_POSITION -> {
                            applyDataSpinner(
                                DistrictOfCity.values()[cityPosition].district,
                                districtPosition
                            ) {
                                districtPosition = it
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) = Unit

                override fun onTabReselected(tab: TabLayout.Tab) = Unit
            })
        }

    }

    private fun applyDataSpinner(@ArrayRes data: Int, defaultPosition: Int, action: (Int) -> Unit) {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                data,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.spinnerCategory.adapter = adapter
                binding.spinnerCategory.setSelection(defaultPosition)
            }
            binding.spinnerCategory.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    private var isFirst: Boolean = true
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        if (isFirst) {
                            isFirst = false
                        } else {
                            action.invoke(position)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                }
        }
    }

    override fun initActions() {
        giftAdapter.onItemClick = {
            findNavController().navigate(
                R.id.giftDetailFragment, bundleOf(
                    Pair(
                        GIFT_ID_KEY, it.id
                    )
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
            selector = { state -> state.listGift },
            observer = { listGift ->
                if (listGift.isNotEmpty()) {
                    giftAdapter.submitList(listGift)
                }
            }
        )
    }


    enum class DistrictOfCity(@ArrayRes val district: Int) {
        DN(R.array.DN),
        HN(R.array.HN),
        HCM(R.array.HCM)
    }
}