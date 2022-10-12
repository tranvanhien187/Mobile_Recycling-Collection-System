package datn.cnpm.rcsystem.feature.history.giftdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.FragmentGiftHistoryDetailBinding
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryFragment
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A simple [GiftHistoryDetailFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GiftHistoryDetailFragment : BaseFragment<FragmentGiftHistoryDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftHistoryDetailBinding
        get() = FragmentGiftHistoryDetailBinding::inflate

    private val viewModel: GiftHistoryDetailViewModel by viewModels()

    @Inject
    lateinit var authPreference: AuthPreference

    override fun initData(data: Bundle?) {
        data?.let { bundle ->
            bundle.getString(GiftHistoryFragment.GIFT_HISTORY_ID_KEY)?.let {
                viewModel.getGiftHistoryDetail(it)
            }
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.exchange_gift_detail_label), R.drawable.ic_back)
        binding.apply {
            context?.let {
                when (authPreference.role) {
                    Role.CUSTOMER.name -> {
                        tvNameLabel1.text = it.getString(R.string.staff_label)
                        tvNameLabel2.text = it.getString(R.string.customer_label)
                    }
                    Role.STAFF.name -> {
                        tvNameLabel1.text = it.getString(R.string.customer_label)
                        tvNameLabel2.text = it.getString(R.string.agent_label)
                    }
                    Role.AGENT.name -> {
                        tvNameLabel1.text = it.getString(R.string.customer_label)
                        tvNameLabel2.text = it.getString(R.string.staff_label)
                    }
                }
            }
        }
    }

    override fun initActions() {
    }

    override fun initObservers() {
        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.loading },
            observer = { loading ->
                if (loading) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        )

        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.giftHistoryDetail },
            observer = { giftHistoryDetail ->
                giftHistoryDetail?.let { data ->
                    context?.let {
                        binding.apply {
                            tvPoint.text = data.point.toString()
                            tvId.text = data.id
                            tvGiftName.text = data.name
                            when (data.status) {
                                HistoryStatus.CREATE.name -> {
                                    tvStatus.createSpannableString(
                                        content = HistoryStatus.CREATE.value,
                                        color = R.color.purple_9747ff
                                    )
                                    tvDateTime.text = data.createAt?.let { date ->
                                        SimpleDateFormat(
                                            "dd MMM yyyy, HH:mm aa",
                                            Locale.UK
                                        ).format(date)
                                    }
                                    tvNameLabel1.gone()
                                    tvNameLabel2.gone()
                                    tvName1.gone()
                                    tvName2.gone()
                                }
                                HistoryStatus.RECEIVE.name -> {
                                    tvStatus.createSpannableString(
                                        content = HistoryStatus.RECEIVE.value,
                                        color = R.color.orange_f99200
                                    )
                                    tvDateTime.text = data.receiveAt?.let { date ->
                                        SimpleDateFormat(
                                            "dd MMM yyyy, HH:mm aa",
                                            Locale.UK
                                        ).format(date)
                                    }
                                    tvNameLabel2.gone()
                                    tvName2.gone()
                                }
                                HistoryStatus.COMPLETE.name -> {
                                    tvStatus.createSpannableString(
                                        content = HistoryStatus.COMPLETE.value,
                                        color = R.color.green_00ad31
                                    )
                                    tvDateTime.text = data.completeAt?.let { date ->
                                        SimpleDateFormat(
                                            "dd MMM yyyy, HH:mm aa",
                                            Locale.UK
                                        ).format(date)
                                    }
                                }
                            }

                            GlideHelper.loadImage(
                                data.evidenceUrl ?: "",
                                imgEvidence,
                                R.drawable.image_default_image_rectangle
                            )
                            tvPoint.text = data.point.toString()


                            when (authPreference.role) {
                                Role.CUSTOMER.name -> {
                                    tvName1.text = data.staffName
                                    tvName2.text = data.agentName
                                }
                                Role.STAFF.name -> {
                                    tvName1.text = data.customerName
                                    tvName2.text = data.agentName
                                }
                                Role.AGENT.name -> {
                                    tvName1.text = data.customerName
                                    tvName2.text = data.staffName
                                }
                            }
                        }
                    }
                }
            }
        )
    }

    fun getStatusIcon(status: String) : Int {
        return when(status) {
            HistoryStatus.CREATE.name -> R.drawable.ic_status_succes
            HistoryStatus.CREATE.name -> R.drawable.ic_status_pending
            HistoryStatus.CREATE.name -> R.drawable.ic_status_fail
            else ->{ R.drawable.ic_status_fail }
        }
    }

    fun getStatusColor(status: String) : Int{
        return when(status) {
            "Success" -> R.color.green_00ad31
            "Pending" -> R.color.orange_f99200
            else -> R.color.red_f34d4d
        }
    }

}