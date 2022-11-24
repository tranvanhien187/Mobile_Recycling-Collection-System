package datn.cnpm.rcsystem.feature.gift.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.databinding.FragmentGiftBinding
import datn.cnpm.rcsystem.feature.gift.detail.GiftDetailFragment.Companion.GIFT_ID_KEY

/**
 * [GiftFragment]
 */
@AndroidEntryPoint
class GiftFragment : BaseFragment<FragmentGiftBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftBinding
        get() = FragmentGiftBinding::inflate

    private val viewModel: GiftViewModel by viewModels()

    private val giftAdapter: GiftAdapter by lazy { GiftAdapter() }
    private val categoryAdapter: GiftCategoryAdapter by lazy { GiftCategoryAdapter() }

    override fun initData(data: Bundle?) {
        viewModel.fetchGift()
    }

    override fun initViews() {
        binding.rvGift.adapter = giftAdapter
        binding.rvCategory.adapter = categoryAdapter
        if (categoryAdapter.currentList.isEmpty()) {
            categoryAdapter.submitList(
                listOf(
                    GiftCategoryModel(GiftCategory.FOOD, R.drawable.food, false),
                    GiftCategoryModel(GiftCategory.BEVERAGE, R.drawable.beverage, false),
                    GiftCategoryModel(GiftCategory.FASHION, R.drawable.fashion, false),
                    GiftCategoryModel(GiftCategory.COSMETIC, R.drawable.cosmetic, false),
                    GiftCategoryModel(GiftCategory.ELECTRONIC, R.drawable.electronic, false),
                    GiftCategoryModel(GiftCategory.HOUSEWARES, R.drawable.houseware, false),
                    GiftCategoryModel(GiftCategory.VOUCHER, R.drawable.voucher, false)
                )
            )
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
        categoryAdapter.apply {
            onItemClick = { data, lastIndex ->
                viewModel.filter(data)
                if (lastIndex != -1) {
                    notifyItemChanged(lastIndex, null)
                }
            }
        }
        binding.apply {
            edtSearch.doAfterTextChanged {
                if (it.toString().isNotEmpty()) {
                    viewModel.performSearch(it.toString())
                    ivDeleteSearch.visible()
                } else {
                    ivDeleteSearch.gone()
                }
            }
            ivDeleteSearch.setOnClickListener {
                edtSearch.setText("")
                viewModel.initList()
            }
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

                binding.apply {
                    listGift?.let {
                        giftAdapter.submitList(listGift)
                        if (it.isNotEmpty()) {
                            llEmptyBox.gone()
                            rvGift.visible()
                        } else {
                            rvGift.gone()
                            llEmptyBox.visible()
                        }
                    }
                }
            }
        )
    }
}