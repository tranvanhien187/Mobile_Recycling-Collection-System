package datn.cnpm.rcsystem.feature.tradingplace.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemGiftByAgentBinding
import datn.cnpm.rcsystem.domain.model.GiftEntity

class GiftOwnerByAgentAdapter : BaseListAdapter<GiftEntity>(GiftUntil()) {

    class GiftUntil : BaseDiffUtilItemCallback<GiftEntity>()

    var onItemClick: (data: GiftEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftByAgentViewHolder(
            ItemGiftByAgentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GiftByAgentViewHolder(val binding: ItemGiftByAgentBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: GiftEntity) {
            binding.apply {
                tvPoint.text = data.redemptionPoint.toPoint()
                tvBrand.text = data.brand.orEmpty()
                tvOwner.text = data.agentName.orEmpty()
                tvGiftName.text = data.name
                tvPlaceName.text = data.placeName
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                GlideHelper.loadImage(
                    data.imageUrl.orEmpty(),
                    ivBanner,
                    R.drawable.ic_person
                )
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }
}
