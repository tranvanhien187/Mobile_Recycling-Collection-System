package datn.cnpm.rcsystem.feature.transportform

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.databinding.ItemTransportGarbageFormBinding
import datn.cnpm.rcsystem.databinding.ItemTransportGiftFormBinding
import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import java.text.SimpleDateFormat


class TransportFormAdapter : BaseListAdapter<TransportFormFirebase>(TransportFormDiffUntil()) {

    class TransportFormDiffUntil : BaseDiffUtilItemCallback<TransportFormFirebase>()

    var onClickItem: (data: TransportFormFirebase) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        return if (viewType == TransportFormType.GARBAGE.value) {
            TransportGarbageFormViewHolder(
                ItemTransportGarbageFormBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            TransportGiftFormViewHolder(
                ItemTransportGiftFormBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].type) {
            TransportFormType.GARBAGE.name -> {
                TransportFormType.GARBAGE.value
            }
            TransportFormType.GIFT.name -> {
                TransportFormType.GIFT.value
            }
            else -> {
                0
            }
        }
    }

    inner class TransportGarbageFormViewHolder(val binding: ItemTransportGarbageFormBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun bind(data: TransportFormFirebase) {
            binding.apply {
                root.setOnClickListener {
                    onClickItem.invoke(data)
                }
                tvGarbageWeight.text = "${data.weight} Kgs"
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(data.createAt)?.let {
                    tvTime.text = SimpleDateFormat("HH:mm").format(it)
                    tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(it)
                }
                tvAddress.text = "${data.street}, ${data.district}, ${data.cityOrProvince}"
            }
        }
    }

    inner class TransportGiftFormViewHolder(val binding: ItemTransportGiftFormBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun bind(data: TransportFormFirebase) {
            binding.apply {
                root.setOnClickListener {
                    onClickItem.invoke(data)
                }
                tvGiftName.text = data.giftName
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(data.createAt)?.let {
                    tvTime.text = SimpleDateFormat("HH:mm").format(it)
                    tvDate.text = SimpleDateFormat("dd/MM/yyyy").format(it)
                }
                tvAddress.text = "${data.street}, ${data.district}, ${data.cityOrProvince}"
            }
        }
    }

    enum class TransportFormType(val value: Int) {
        GARBAGE(1),
        GIFT(2)
    }
}