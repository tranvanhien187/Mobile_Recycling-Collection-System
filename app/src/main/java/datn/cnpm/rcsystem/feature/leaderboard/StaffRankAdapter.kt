package datn.cnpm.rcsystem.feature.leaderboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.ItemRankBinding
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectEntity


class StaffRankAdapter : BaseListAdapter<StatisticStaffCollectEntity>(StaffCollectUtil()) {

    class StaffCollectUtil : BaseDiffUtilItemCallback<StatisticStaffCollectEntity>()

    var onItemClick: (data: StatisticStaffCollectEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftByAgentViewHolder(
            ItemRankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GiftByAgentViewHolder(val binding: ItemRankBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: StatisticStaffCollectEntity) {
            binding.apply {
                tvName.text = data.name
                tvRank.text = "${adapterPosition + 3}"
                GlideHelper.loadImage(
                    data.avatarUrl.orEmpty(),
                    ivAvatar,
                    R.drawable.ic_person
                )
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }
}