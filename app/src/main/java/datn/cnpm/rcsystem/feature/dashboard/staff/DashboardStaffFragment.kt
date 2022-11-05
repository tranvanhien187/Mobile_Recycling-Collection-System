package datn.cnpm.rcsystem.feature.dashboard.staff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.createSpannableString
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.extension.visible
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentStaffDashboardBinding
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectWeightByDayEntity
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.feature.transportform.TransportFormAdapter
import datn.cnpm.rcsystem.feature.transportform.mission.MissionFragment
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * [DashboardStaffFragment]
 */
@AndroidEntryPoint
class DashboardStaffFragment : BaseFragment<FragmentStaffDashboardBinding>() {

    companion object {
        private const val CHART_COLUMN_NUMBER = 7
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStaffDashboardBinding
        get() = FragmentStaffDashboardBinding::inflate

    @Inject
    lateinit var authPreference: AuthPreference
    private val viewModel: DashboardStaffViewModel by viewModels()

    override fun isDisableFullScreen(): Boolean = false
    override fun initData(data: Bundle?) {
        viewModel.fetchStaffInfo()
    }

    override fun initViews() {
    }

    override fun initActions() {

        binding.apply {
            ivForm.setOnClickListener {
                findNavController().navigate(R.id.transportFormFragment)
            }
            ivShipping.setOnClickListener {
                findNavController().navigate(R.id.missionFragment)
            }
            ivHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            ivTrophy.setOnClickListener {
                findNavController().navigate(R.id.leaderboardFragment)
            }
            ivViewAll.setOnClickListener {
                findNavController().navigate(R.id.missionFragment)
            }
            btnMarkComplete.setOnClickListener {
                viewModel.currentState.form?.let {
                    if (it.type == TransportFormAdapter.TransportFormType.GIFT.name) {
                        findNavController().navigate(
                            R.id.completeGiftFormFragment,
                            bundleOf(Pair(MissionFragment.FORM_KEY, it))
                        )
                    } else if (it.type == TransportFormAdapter.TransportFormType.GARBAGE.name) {
                        findNavController().navigate(
                            R.id.completeFormFragment,
                            bundleOf(Pair(MissionFragment.FORM_KEY, it))
                        )
                    }
                }
            }

            btnSetting.setOnClickListener {
                val intent =
                    Intent(this@DashboardStaffFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.isRememberMe = false
            }
        }
    }

    override fun initObservers() {

        viewModel.apply {
            observe(
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

            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.form },
                observer = { form ->
                    binding.apply {
                        form?.let {
                            tvAddress.text =
                                "${form.street}, ${form.district}, ${form.cityOrProvince}"
                            tvName.text = form.customerName
                            tvPhoneNumber.text = form.customerPhoneNumber
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(form.receiveAt.toString())
                                ?.let {
                                    tvDateTime.text =
                                        SimpleDateFormat("dd MMM yyyy, HH:mm aa", Locale.UK).format(
                                            it
                                        )
                                }
                            if (form.type == TransportFormAdapter.TransportFormType.GARBAGE.name) {
                                tvOrderTypeLabel.text = getString(R.string.weight_label)
                                tvOrderDescription.text = "${form.weight}Kg"
                            } else {
                                tvOrderTypeLabel.text = getString(R.string.gift_name_label)
                                tvOrderDescription.text = "${form.giftName}"
                            }
                            llNoSchedule.gone()
                            clForm.visible()
                        } ?: kotlin.run {
                            llNoSchedule.visible()
                        }
                    }
                }
            )
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.staff },
                observer = { staff ->
                    staff?.let {
                        GlideHelper.loadImage(
                            staff.avatar,
                            binding.ivAvatar,
                            R.drawable.ic_person
                        )

                        val garbageCount = "${it.weightTotal} kgs"
                        val giftCount = "${it.giftCount} gift"
                        val count = getString(
                            R.string.label_total_weight_and_gift,
                            garbageCount, giftCount
                        )
                        binding.tvGarbageWeight.createSpannableString(
                            startIndex = 16,
                            endIndex = 16 + garbageCount.length,
                            isBoldClickableContent = false,
                            color = R.color.orange_f05a30,
                            content = count
                        )
                        binding.tvGarbageWeight.createSpannableString(
                            startIndex = 33 + garbageCount.length,
                            endIndex = 33 + giftCount.length + garbageCount.length,
                            isBoldClickableContent = false,
                            color = R.color.green_00ad31
                        )
                        binding.tvUsername.text = staff.name
                        binding.tvTotalWeightValue.text = staff.weightTotal.toString()
                    }
                }
            )
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.staffCollection7Day },
                observer = { data ->
                    handleDataChart(data.orEmpty())
                }
            )
        }
    }

    private fun handleDataChart(datas: List<StatisticStaffCollectWeightByDayEntity>) {
        if (datas.isEmpty()) return
        val entries: MutableList<BarEntry> = ArrayList()
        val xAxisLabel: ArrayList<String> = ArrayList()
        for (i in 0 until CHART_COLUMN_NUMBER) {
            if (i < datas.size) {
                entries.add(BarEntry(i.toFloat(), datas[i].totalWeight.toFloat()))
                xAxisLabel.add(
                    getString(
                        R.string.month_year,
                        datas[i].datOfMonth,
                        datas[i].monthOfYear
                    )
                )
            } else {
                entries.add(BarEntry(i.toFloat(), 0f))
                xAxisLabel.add("")
            }
        }
        val set = BarDataSet(entries, "")
        set.colors = mutableListOf(
            ContextCompat.getColor(requireContext(), R.color.red_f94144),
            ContextCompat.getColor(requireContext(), R.color.orange_f3722c),
            ContextCompat.getColor(requireContext(), R.color.orange_f8961e),
            ContextCompat.getColor(requireContext(), R.color.yellow_f9c74f),
            ContextCompat.getColor(requireContext(), R.color.green_90be6d),
            ContextCompat.getColor(requireContext(), R.color.blue_2d9cdb),
            ContextCompat.getColor(requireContext(), R.color.purple_7f03a1)
        )
        val chartData = BarData(set)
        chartData.barWidth = 0.6f
        chartData.setValueTextSize(10f)
        chartData.setValueTypeface(
            ResourcesCompat.getFont(
                requireContext(),
                R.font.proximanova_reg
            )
        )

        binding.chartWeighPerDay.run {
            data = chartData
            setFitBars(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            val formatter: ValueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return xAxisLabel[value.toInt()]
                }
            }
            xAxis.valueFormatter = formatter
            axisLeft.typeface = ResourcesCompat.getFont(requireContext(), R.font.proximanova_reg)
            xAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.proximanova_reg)
            axisLeft.xOffset = 9f
            xAxis.granularity = 1f
            axisLeft.axisMinimum = (0F)
            legend.isEnabled = false
            description.isEnabled = false
            setTouchEnabled(false)
            axisRight.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)
            axisRight.isEnabled = false
            xAxis.setDrawGridLines(false)
        }
        binding.chartWeighPerDay.invalidate()
    }
}
