package datn.cnpm.rcsystem.feature.gift.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemTradingPlaceBinding
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

class GiftAdapter : BaseListAdapter<TradingPlaceForUserEntity>(TradingPlaceDiffUntil()) {

    class TradingPlaceDiffUntil : BaseDiffUtilItemCallback<TradingPlaceForUserEntity>()

    var onItemClick: (data: TradingPlaceForUserEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        TradingPlaceViewHolder(
            ItemTradingPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class TradingPlaceViewHolder(val binding: ItemTradingPlaceBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: TradingPlaceForUserEntity) {
            binding.apply {
                tvName.text = data.name.orEmpty()
                tvDealer.text = data.dealerName.orEmpty()
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                GlideHelper.loadImage(
                    data.bannerUrl.orEmpty(),
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