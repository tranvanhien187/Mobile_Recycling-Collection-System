package datn.cnpm.rcsystem.feature.history.gift

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.basesource.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.databinding.ItemGiftHistoryBinding
import datn.cnpm.rcsystem.domain.model.GiftStatus
import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity
import java.text.SimpleDateFormat

class GiftAdapter : BaseListAdapter<GiftUserHistoryEntity>(GiftDiffUntil()) {
    class GiftDiffUntil : BaseDiffUtilItemCallback<GiftUserHistoryEntity>()

    var onItemClick: (gift: GiftUserHistoryEntity) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftViewHolder(
            ItemGiftHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GiftViewHolder(val binding: ItemGiftHistoryBinding) :
        BaseItemViewHolder(binding.root) {

        init {

            binding.clGift.setOnClickListener {
                onItemClick.invoke(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun bind(data: GiftUserHistoryEntity) {
            binding.apply {
                tvProductName.text = data.name.orEmpty()
                tvDealerName.text = data.dealerName.orEmpty()
                tvContributor.text = data.contributor.orEmpty()
                tvRedemptionPoint.text =
                    root.context?.getString(R.string.redemption_point, data.redemptionPoint)
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                when (data.status) {
                    GiftStatus.AVAILABLE.name -> {}
                    GiftStatus.RECEIVED.name -> {
                        tvRegister.gone()
                        tvReceive.visible()
                        data.receivedTime?.let {
                            tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(it)
                        }
                    }
                    GiftStatus.REGISTER.name -> {
                        tvReceive.gone()
                        tvRegister.visible()
                        data.registerTime?.let {
                            tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(it)
                        }
                    }
                }
                GlideHelper.loadImage(
                    data.imageUrl.orEmpty(),
                    ivProduct,
                    R.drawable.image_default_image_rectangle
                )
            }
        }
    }
}