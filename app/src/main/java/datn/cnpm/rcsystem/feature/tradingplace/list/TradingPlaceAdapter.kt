package datn.cnpm.rcsystem.feature.tradingplace.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemTradingPlaceBinding
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

class TradingPlaceAdapter : BaseListAdapter<TradingPlaceForUserEntity>(TradingPlaceDiffUntil()) {

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
                ivLevel.run {
                    when (data.rank) {
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
                root.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }
}