package datn.cnpm.rcsystem.feature.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.databinding.ItemGiftHomeBinding
import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.GiftType


class GiftYouMayBeLikeAdapter : BaseListAdapter<GiftEntity>(GiftDiffUtil()) {

    class GiftDiffUtil : BaseDiffUtilItemCallback<GiftEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        TradingPlaceViewHolder(
            ItemGiftHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class TradingPlaceViewHolder(val binding: ItemGiftHomeBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: GiftEntity) {
            binding.apply {
                tvGiftName.text = data.name
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                tvPoint.text = data.redemptionPoint.toPoint()
                GlideHelper.loadImage(
                    data.imageUrl.orEmpty(),
                    ivBanner,
                    R.drawable.image_default_image_rectangle
                )
                when (data.type) {
                    GiftType.Houseware.name -> {
                        tvType.background = ContextCompat.getDrawable(
                            root.context,
                            R.drawable.background_houseware_gift_type
                        )
                    }
                    GiftType.Beverage.name -> {
                        tvType.background = ContextCompat.getDrawable(
                            root.context,
                            R.drawable.background_beverage_gift_type
                        )
                    }
                    GiftType.Electronic.name -> {
                        tvType.background = ContextCompat.getDrawable(
                            root.context,
                            R.drawable.background_electronic_gift_type
                        )
                    }
                }
                tvType.text = data.type
            }
        }
    }
}