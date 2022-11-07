package datn.cnpm.rcsystem.feature.gift.mygift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentMyGiftBinding
import datn.cnpm.rcsystem.feature.gift.detail.GiftDetailFragment


@AndroidEntryPoint
class MyGiftFragment : BaseFragment<FragmentMyGiftBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyGiftBinding
        get() = FragmentMyGiftBinding::inflate

    private val viewModel: MyGiftViewModel by viewModels()

    private val giftAdapter: MyGiftAdapter by lazy { MyGiftAdapter() }

    override fun initData(data: Bundle?) {
        viewModel.fetchMyGift()
    }

    override fun initViews() {
        binding.rvGift.adapter = giftAdapter
    }

    override fun initActions() {
        giftAdapter.onItemClick = {
            findNavController().navigate(
                R.id.giftDetailFragment, bundleOf(
                    Pair(
                        GiftDetailFragment.GIFT_ID_KEY, it.id
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
}