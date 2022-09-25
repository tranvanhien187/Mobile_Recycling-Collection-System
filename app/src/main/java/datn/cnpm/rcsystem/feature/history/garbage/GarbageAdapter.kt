package datn.cnpm.rcsystem.feature.history.garbage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.basesource.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.databinding.ItemGarbageHistoryBinding
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity
import java.text.SimpleDateFormat


class GarbageAdapter : BaseListAdapter<GarbageUserHistoryEntity>(GarbageDiffUntil()) {
    class GarbageDiffUntil : BaseDiffUtilItemCallback<GarbageUserHistoryEntity>()

    var onItemClick: (gift: GarbageUserHistoryEntity) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftViewHolder(
            ItemGarbageHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GiftViewHolder(val binding: ItemGarbageHistoryBinding) :
        BaseItemViewHolder(binding.root) {

        init {

            binding.clGarbage.setOnClickListener {
                onItemClick.invoke(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun bind(data: GarbageUserHistoryEntity) {
            binding.apply {
                tvTPlaceName.text = data.placeName.orEmpty()
                tvDealerName.text = data.dealerName.orEmpty()
                tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                tvWeight.text = data.weight.toString()
                data.time?.let {
                    tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(it)
                }
                GlideHelper.loadImage(
                    data.placeUrl.orEmpty(),
                    ivTPlace,
                    R.drawable.image_default_image_rectangle
                )
            }
        }
    }
}