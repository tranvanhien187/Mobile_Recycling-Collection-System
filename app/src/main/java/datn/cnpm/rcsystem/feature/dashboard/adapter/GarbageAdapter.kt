package datn.cnpm.rcsystem.feature.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.databinding.ItemRecyclingGarbageBinding


class GarbageAdapter : BaseListAdapter<RecyclingGarbage>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GarbageViewHolder(
            ItemRecyclingGarbageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GarbageViewHolder(private val binding: ItemRecyclingGarbageBinding) :
        BaseItemViewHolder(binding.root) {

        override fun bind(data: RecyclingGarbage) {
            super.bind(data)
            binding.ivGarbage.setImageResource(data.resource)
        }
    }
}