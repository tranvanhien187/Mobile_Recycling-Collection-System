package datn.cnpm.rcsystem.feature.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseListAdapter
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.databinding.ItemHistoryBinding
import datn.cnpm.rcsystem.domain.model.history.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryItemAdapter : BaseListAdapter<BaseItemHistory>() {

    internal var onItemClick: (BaseItemHistory) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GarbageExchangeHistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class GarbageExchangeHistoryViewHolder(private val binding: ItemHistoryBinding) :
        BaseItemViewHolder(binding.root) {

        override fun bind(data: BaseItemHistory) {
            when (data) {
                is ItemGarbageHistoryByAgentEntity -> {
                    handleGarbageHistoryByAgent(data, binding)
                }
                is ItemGarbageHistoryByStaffEntity -> {
                    handleGarbageHistoryByStaff(data, binding)
                }
                is ItemGarbageHistoryByCustomerEntity -> {
                    handleGarbageHistoryByCustomer(data, binding)
                }
                is ItemGiftHistoryByAgentEntity -> {
                    handleGiftHistoryByAgent(data, binding)
                }
                is ItemGiftHistoryByStaffEntity -> {
                    handleGiftHistoryByStaff(data, binding)
                }
                is ItemGiftHistoryByCustomerEntity -> {
                    handleGiftHistoryByCustomer(data, binding)
                }
                else -> {}
            }
            binding.root.setOnClickListener {
                onItemClick.invoke(data)
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGarbageHistoryByAgent(
            data: ItemGarbageHistoryByAgentEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvFrom.visible()
                            tvFrom.text = data.customerName
                            tvGiftPoint.text = data.point.toString()
                        }
                        HistoryStatus.CREATE.name -> {}
                        HistoryStatus.RECEIVE.name -> {}
                        HistoryStatus.CANCEL.name -> {}
                        else -> {}
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGarbageHistoryByCustomer(
            data: ItemGarbageHistoryByCustomerEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.CREATE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.CREATE.value,
                                color = R.color.purple_9747ff
                            )
                            tvDate.text = it.getDateStringDMY(data.createAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.gone()
                        }
                        HistoryStatus.RECEIVE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.RECEIVE.value,
                                color = R.color.orange_f99200
                            )
                            tvDate.text = it.getDateStringDMY(data.receiveAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvFrom.visible()
                            tvFrom.text = data.staffName
                            tvGiftPoint.text = data.point.toString()
                        }
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.visible()
                            tvFrom.text = data.agentName
                        }
                        HistoryStatus.CANCEL.name -> {}
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGarbageHistoryByStaff(
            data: ItemGarbageHistoryByStaffEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.RECEIVE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.RECEIVE.value,
                                color = R.color.orange_f99200
                            )
                            tvDate.text = it.getDateStringDMY(data.receiveAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.visible()
                            tvFrom.text = data.customerName
                        }
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvArtifact.text = it.getWeightString(data.weight)
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.visible()
                            tvFrom.text = data.customerName
                        }
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGiftHistoryByAgent(
            data: ItemGiftHistoryByAgentEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvGiftPoint.text = data.point.toString()
                            tvArtifact.text = data.giftName
                            tvFrom.visible()
                            tvFrom.text = data.customerName
                        }
                        HistoryStatus.CREATE.name -> {}
                        HistoryStatus.RECEIVE.name -> {}
                        HistoryStatus.CANCEL.name -> {}
                        else -> {}
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGiftHistoryByCustomer(
            data: ItemGiftHistoryByCustomerEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.CREATE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.CREATE.value,
                                color = R.color.purple_9747ff
                            )
                            tvDate.text = it.getDateStringDMY(data.createAt)
                            tvGiftPoint.text = data.point.toString()
                            tvArtifact.text = data.giftName
                            tvFrom.gone()
                        }
                        HistoryStatus.RECEIVE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.RECEIVE.value,
                                color = R.color.orange_f99200
                            )
                            tvDate.text = it.getDateStringDMY(data.receiveAt)
                            tvGiftPoint.text = data.point.toString()
                            tvArtifact.text = data.giftName
                            tvFrom.visible()
                            tvFrom.text = data.staffName
                        }
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvArtifact.text = data.giftName
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.visible()
                            tvFrom.text = data.agentName
                        }
                        HistoryStatus.CANCEL.name -> {}
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        private fun handleGiftHistoryByStaff(
            data: ItemGiftHistoryByStaffEntity,
            binding: ItemHistoryBinding
        ) {
            binding.apply {
                root.context?.let {
                    when (data.status) {
                        HistoryStatus.RECEIVE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.RECEIVE.value,
                                color = R.color.orange_f99200
                            )
                            tvDate.text = it.getDateStringDMY(data.receiveAt)
                            tvArtifact.text = data.giftName
                            tvFrom.visible()
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.text = data.customerName
                        }
                        HistoryStatus.COMPLETE.name -> {
                            tvStatus.createSpannableString(
                                content = HistoryStatus.COMPLETE.value,
                                color = R.color.green_00ad31
                            )
                            tvDate.text = it.getDateStringDMY(data.completeAt)
                            tvArtifact.text = data.giftName
                            tvGiftPoint.text = data.point.toString()
                            tvFrom.visible()
                            tvFrom.text = data.customerName
                        }
                    }
                }
            }
        }

        @SuppressLint("SimpleDateFormat")
        private fun Context.getDateStringDMY(date: Date): String {
            return getString(
                R.string.latest_update,
                SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
            )
        }

        @SuppressLint("SimpleDateFormat")
        private fun Context.getWeightString(weight: Float): String {
            return getString(R.string.equivalent_weight, "${weight}Kgs")
        }
    }
}