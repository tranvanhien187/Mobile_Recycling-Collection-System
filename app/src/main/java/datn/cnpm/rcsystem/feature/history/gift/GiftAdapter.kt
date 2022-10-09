package datn.cnpm.rcsystem.feature.history.gift

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.databinding.ItemGiftHistoryBinding

class GiftAdapter : BaseListAdapter<GiftExchangeHistory>() {

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
                tvFrom.text =
                    binding.root.context.getString(R.string.from_agent_name, data.agentName)
                tvStatus.text = data.status
                tvStatus.setTextColor(ContextCompat.getColor(root.context, data.getStatusColor()))
                tvCreateDate.text = data.createAt

                binding.root.setOnClickListener {
                    onItemClick.invoke(data.id)
                }
            }
        }
    }
}
