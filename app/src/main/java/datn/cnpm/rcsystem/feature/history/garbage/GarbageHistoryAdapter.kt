package datn.cnpm.rcsystem.feature.history.garbage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.databinding.ItemGarbageHistoryBinding
import datn.cnpm.rcsystem.databinding.ItemGiftHistoryBinding
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity
import java.text.SimpleDateFormat


class GarbageHistoryAdapter : BaseListAdapter<GiftExchangeHistory>() {

    internal var onItemClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftExchangeHistoryViewHolder(
            ItemGiftHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

    inner class GiftExchangeHistoryViewHolder(private val binding: ItemGiftHistoryBinding) :
        BaseItemViewHolder(binding.root) {

        override fun bind(data: GiftExchangeHistory) {
            super.bind(data)
            binding.run {
                tvGiftName.text = data.name
                tvGiftPoint.text = data.point.toString()
                tvFrom.text = binding.root.context.getString(R.string.from_agent_name, data.agentName)
                tvStatus.text = data.status
                tvCreateDate.text = data.createAt

                binding.root.setOnClickListener {
                    onItemClick.invoke(data.id)
                }
            }
        }
    }
}
