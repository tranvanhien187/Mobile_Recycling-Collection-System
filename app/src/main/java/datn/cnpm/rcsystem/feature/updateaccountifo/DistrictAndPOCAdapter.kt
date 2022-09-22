package datn.cnpm.rcsystem.feature.updateaccountifo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.databinding.ItemDistrictAndPocBinding

class DistrictAndPOCAdapter : BaseListAdapter<String>(DistrictAndPOCDiffUntil()) {

    var onItemClick : (data: String, type: Type) -> Unit = { _: String, _: Type -> }
    var type: Type = Type.ProvinceOrCity
    class DistrictAndPOCDiffUntil : BaseDiffUtilItemCallback<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        DistrictAndPOCViewHolder(
            ItemDistrictAndPocBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class DistrictAndPOCViewHolder(val binding: ItemDistrictAndPocBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: String) {
            binding.apply {
                root.setOnClickListener {
                    ivCheck.visible()
                    onItemClick.invoke(data, type)
                }
                tvPlace.text = data
            }
        }
    }

    enum class Type {
        District,
        ProvinceOrCity
    }
}