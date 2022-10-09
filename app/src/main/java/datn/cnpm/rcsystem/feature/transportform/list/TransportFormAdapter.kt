package datn.cnpm.rcsystem.feature.transportform.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.databinding.ItemTransportFormBinding
import datn.cnpm.rcsystem.domain.model.TransportForm
import java.text.SimpleDateFormat

class TransportFormAdapter : BaseListAdapter<TransportForm>(TransportFormDiffUntil()) {

    class TransportFormDiffUntil : BaseDiffUtilItemCallback<TransportForm>()

    var onClickItem: (data: TransportForm) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        TradingPlaceViewHolder(
            ItemTransportFormBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class TradingPlaceViewHolder(val binding: ItemTransportFormBinding) :
        BaseItemViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun bind(data: TransportForm) {
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
}