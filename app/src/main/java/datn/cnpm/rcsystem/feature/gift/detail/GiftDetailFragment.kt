package datn.cnpm.rcsystem.feature.gift.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentGiftDetailBinding

@AndroidEntryPoint
class GiftDetailFragment : BaseFragment<FragmentGiftDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftDetailBinding
        get() = FragmentGiftDetailBinding::inflate

    private val viewModel: GiftDetailViewModel by viewModels()

    companion object {
        const val GIFT_ID_KEY = "GIFT_ID_KEY"

    }

    override fun initData(data: Bundle?) {
        data?.let {
            it.getString(GIFT_ID_KEY)?.let { giftId ->
                viewModel.fetchGiftDetail(giftId)
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
            selector = { state -> state.gift },
            observer = { gift ->
                gift?.let {
                    binding.apply {
                        tvName.text = it.name
                        tvBrand.text = it.brand
                        tvPhoneNumber.text = it.agentPhone
                        tvAgentName.text = it.agentName
                        tvPoint.text = "${it.redemptionPoint}"
                        tvAddress.text = "${it.street}, ${it.district}, ${it.provinceOrCity}"
                        GlideHelper.loadImage(
                            it.url ?: "",
                            imgBanner,
                            R.drawable.image_default_image_rectangle
                        )
                    }
                }
            }
        )
    }
}
