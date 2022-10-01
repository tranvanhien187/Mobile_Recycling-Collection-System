package datn.cnpm.rcsystem.feature.history.giftdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGiftHistoryDetailBinding
import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryFragment

/**
 * A simple [GiftHistoryDetailFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GiftHistoryDetailFragment : BaseFragment<FragmentGiftHistoryDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftHistoryDetailBinding
        get() = FragmentGiftHistoryDetailBinding::inflate

    private val viewModel: GiftHistoryDetailViewModel by viewModels()

    override fun initData(data: Bundle?) {
        data?.let { bundle ->
            bundle.getParcelable<GiftUserHistoryEntity>(GiftHistoryFragment.GIFT_HISTORY_KEY)?.let {
                viewModel.initData(it)
            }
        }
    }

    override fun initViews() {

    }

    override fun initActions() {
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
            selector = { state -> state.giftHistory },
            observer = { giftHistory ->
                giftHistory?.let {
                }
            }
        )
    }

}