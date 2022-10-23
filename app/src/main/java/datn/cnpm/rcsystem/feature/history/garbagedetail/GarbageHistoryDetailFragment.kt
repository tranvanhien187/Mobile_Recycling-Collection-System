package datn.cnpm.rcsystem.feature.history.garbagedetail

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
import datn.cnpm.rcsystem.databinding.FragmentGarbageHistoryDetailBinding
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import datn.cnpm.rcsystem.feature.history.garbage.GarbageHistoryFragment
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A simple [GarbageHistoryDetailFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GarbageHistoryDetailFragment : BaseFragment<FragmentGarbageHistoryDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGarbageHistoryDetailBinding
        get() = FragmentGarbageHistoryDetailBinding::inflate

    private val viewModel: GarbageHistoryDetailViewModel by viewModels()

    @Inject
    lateinit var authPreference: AuthPreference

    override fun initData(data: Bundle?) {
        data?.let { bundle ->
            bundle.getString(GarbageHistoryFragment.GARBAGE_HISTORY_ID_KEY)?.let {
                viewModel.getGarbageHistoryDetail(it)
            }
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.exchange_garbage_detail_label), R.drawable.ic_back)
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
            selector = { state -> state.garbageHistoryDetail },
            observer = { giftHistoryDetail ->
                giftHistoryDetail?.let { data ->
                    context?.let {
                        binding.apply {
                            tvPoint.text = data.point.toString()
                            tvId.text = data.id
                            tvEquivalent.text = "${data.weight}Kgs"
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
                                    imgStatus.setImageResource(R.drawable.ic_create)
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
                                    imgStatus.setImageResource(R.drawable.ic_driving)
                                    tvNameLabel2.gone()
                                    tvName2.gone()
                                }
                                HistoryStatus.COMPLETE.name -> {
                                    tvStatus.createSpannableString(
                                        content = HistoryStatus.COMPLETE.value,
                                        color = R.color.green_00ad31
                                    )
                                    imgStatus.setImageResource(R.drawable.ic_complete)
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
}
