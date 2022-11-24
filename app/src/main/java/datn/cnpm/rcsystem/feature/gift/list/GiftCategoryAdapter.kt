package datn.cnpm.rcsystem.feature.gift.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.databinding.ItemGiftCategoryBinding


class GiftCategoryAdapter : BaseListAdapter<GiftCategoryModel>(GiftCategoryUntil()) {

    class GiftCategoryUntil : BaseDiffUtilItemCallback<GiftCategoryModel>()

    var lastItemCheck = -1
    var onItemClick: (data: GiftCategoryModel?, lastIndex: Int) -> Unit =
        { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GiftCategoryViewHolder(
            ItemGiftCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GiftCategoryViewHolder(val binding: ItemGiftCategoryBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: GiftCategoryModel) {
            binding.apply {
                tvCategory.text = data.category.value
                ivCategory.setImageResource(data.src)
                if (data.isCheck) {
                    vCheck.visible()
                } else {
                    vCheck.gone()
                }
                root.setOnClickListener {
                    if (lastItemCheck != adapterPosition) {
                        onItemClick.invoke(data, lastItemCheck)
                        if (lastItemCheck != -1) {
                            currentList[lastItemCheck].isCheck = false
                        }
                        data.isCheck = true
                        notifyItemChanged(adapterPosition, null)
                        lastItemCheck = adapterPosition
                    } else {
                        lastItemCheck = -1
                        onItemClick.invoke(null, lastItemCheck)
                        data.isCheck = false
                        notifyItemChanged(adapterPosition, null)
                    }
                }

            }
        }
    }
}