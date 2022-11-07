package datn.cnpm.rcsystem.feature.tradingplace.mytradingplace

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.CommonUtils.toWeight
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentMyTradingPlaceBinding
import datn.cnpm.rcsystem.feature.tradingplace.detail.TradingPlaceDetailViewModel

/**
 * [MyTradingPlaceFragment]
 */
@AndroidEntryPoint
class MyTradingPlaceFragment : BaseFragment<FragmentMyTradingPlaceBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyTradingPlaceBinding
        get() = FragmentMyTradingPlaceBinding::inflate

    private val viewModel: TradingPlaceDetailViewModel by viewModels()

    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) {
        SingletonObject.agent?.let {
            it.tradingPlace?.id?.let { tPlaceId ->
                viewModel.fetchTPlaceDetail(tPlaceId)
            }
        }
    }

    override fun initViews() {
    }

    override fun initActions() {
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
                        tvTotalWeightValue.text = it.totalWeight.toWeight()
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
                    }
                }
            }
        )
    }
}
