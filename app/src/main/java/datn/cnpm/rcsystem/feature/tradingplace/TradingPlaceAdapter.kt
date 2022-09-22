package datn.cnpm.rcsystem.feature.tradingplace

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.basesource.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.databinding.ItemTradingPlaceBinding
import datn.cnpm.rcsystem.domain.model.TradingPlaceForUserEntity

class TradingPlaceAdapter : BaseListAdapter<TradingPlaceForUserEntity>(TradingPlaceDiffUntil()) {

    class TradingPlaceDiffUntil : BaseDiffUtilItemCallback<TradingPlaceForUserEntity>()

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
            }
        }
    }
}