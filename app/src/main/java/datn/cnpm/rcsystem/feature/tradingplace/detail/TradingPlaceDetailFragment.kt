package datn.cnpm.rcsystem.feature.tradingplace.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentPlaceDetailBinding
import datn.cnpm.rcsystem.feature.gift.detail.GiftDetailFragment

/**
 * [TradingPlaceDetailFragment]
 */
@AndroidEntryPoint
class TradingPlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPlaceDetailBinding
        get() = FragmentPlaceDetailBinding::inflate

    private val viewModel: TradingPlaceDetailViewModel by viewModels()
    private val adapter: GiftOwnerByAgentAdapter by lazy { GiftOwnerByAgentAdapter() }

    companion object {
        const val TRADING_PLACE_ID_KEY = "TRADING_PLACE_ID_KEY"
        const val TRADING_PLACE_AGENT_ID_KEY = "TRADING_PLACE_AGENT_ID_KEY"

    }

    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) {
        data?.let {
            it.getString(TRADING_PLACE_ID_KEY)?.let { ownerId ->
                viewModel.fetchTPlaceDetail(ownerId)
            }
            it.getString(TRADING_PLACE_AGENT_ID_KEY)?.let { agentId ->
                viewModel.fetchGiftByAgentAdapter(agentId)
            }
        }
    }

    override fun initViews() {
        binding.rlGiftOwnerByAgent.adapter = adapter
    }

    override fun initActions() {
        adapter.onItemClick = {
            findNavController().navigate(R.id.giftDetailFragment,
                bundleOf(GiftDetailFragment.GIFT_ID_KEY to it.id))
        }
    }

    @SuppressLint("SetTextI18n")
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
            selector = { state -> state.tplace },
            observer = { tplace ->
                tplace?.let {
                    binding.apply {
                        tvName.text = it.name
                        tvAgentName.text = it.agentName
                        tvPhoneNumber.text = it.agentPhoneNumber
                        tvTotalWeightValue.text = "${it.totalWeight}Kgs"
                        tvTotalGiftValue.text = "${it.giftExchange} Gifts"
                        tvAddress.text = "${it.street}, ${it.district}, ${it.provinceOrCity}"
                        GlideHelper.loadImage(
                            it.bannerUrl ?: "",
                            imgBanner,
                            R.drawable.image_default_image_rectangle
                        )
                        ivLevel.run {
                            when (it.rank) {
                                1 -> {
                                    setImageResource(R.drawable.ic_level_1)
                                }
                                2 -> {
                                    setImageResource(R.drawable.ic_level_2)
                                }
                                3 -> {
                                    setImageResource(R.drawable.ic_level_3)
                                }
                                4 -> {
                                    setImageResource(R.drawable.ic_level_4)
                                }
                                5 -> {
                                    setImageResource(R.drawable.ic_level_5)
                                }
                            }
                        }
                        btnSchedule.setOnClickListener {
                            findNavController().navigate(R.id.createFormFragment)
                        }
                    }
                }
            }
        )
        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.listGiftOwnerByAgent },
            observer = { listGift ->
                adapter.submitList(listGift)
            }
        )
    }
}
