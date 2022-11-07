package datn.cnpm.rcsystem.feature.gift.mygift

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemMyGiftBinding
import datn.cnpm.rcsystem.domain.model.gift.GiftEntity
import datn.cnpm.rcsystem.domain.model.gift.GiftStatus


class MyGiftAdapter : BaseListAdapter<GiftEntity>(GiftUntil()) {

    class GiftUntil : BaseDiffUtilItemCallback<GiftEntity>()

    var onItemClick: (data: GiftEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        MyGiftViewHolder(
            ItemMyGiftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class MyGiftViewHolder(val binding: ItemMyGiftBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: GiftEntity) {
            binding.apply {
                tvPoint.text = data.redemptionPoint.toPoint()
                tvGiftName.text = data.name.orEmpty()
                tvGiftBrand.text = data.brand.orEmpty()
                when (data.status) {
                    GiftStatus.AVAILABLE.name -> {
                        tvStatus.createSpannableString(
                            content = "Available",
                            color = R.color.green_00ad31
                        )
                        vLock.gone()
                    }
                    GiftStatus.REGISTER.name -> {
                        tvStatus.createSpannableString(
                            content = "Ordered",
                            color = R.color.yellow_fff402
                        )
                        vLock.visible()
                    }
                    GiftStatus.RECEIVED.name -> {
                        tvStatus.createSpannableString(
                            content = "Exchanged",
                            color = R.color.red_f94144
                        )
                        vLock.gone()
                    }
                }
                GlideHelper.loadImage(
                    data.imageUrl.orEmpty(),
                    ivGift,
                    R.drawable.gift_default
                )
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }
}