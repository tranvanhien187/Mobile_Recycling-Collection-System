package datn.cnpm.rcsystem.feature.gift.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemGiftBinding
import datn.cnpm.rcsystem.domain.model.GiftEntity

class GiftAdapter : BaseListAdapter<GiftEntity>(GiftUntil()) {

    class GiftUntil : BaseDiffUtilItemCallback<GiftEntity>()

    var onItemClick: (data: GiftEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        TradingPlaceViewHolder(
            ItemGiftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class TradingPlaceViewHolder(val binding: ItemGiftBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: GiftEntity) {
            binding.apply {
                tvPoint.text = data.redemptionPoint.toPoint()
                tvName.text = data.name.orEmpty()
                tvBrand.text = data.brand.orEmpty()
                tvTPlaceName.text = data.placeName.orEmpty()
                tvAgent.text = data.agentName.orEmpty()
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                GlideHelper.loadImage(
                    data.imageUrl.orEmpty(),
                    ivGift,
                    R.drawable.ic_person
                )
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }
}