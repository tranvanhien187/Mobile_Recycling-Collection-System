package datn.cnpm.rcsystem.feature.dashboard.customer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentCustomerDashboardBinding
import datn.cnpm.rcsystem.feature.authentication.AuthenticationActivity
import datn.cnpm.rcsystem.feature.dashboard.RecyclingGarbage
import datn.cnpm.rcsystem.feature.dashboard.adapter.GarbageAdapter
import datn.cnpm.rcsystem.feature.dashboard.adapter.GiftYouMayBeLikeAdapter
import datn.cnpm.rcsystem.feature.dashboard.adapter.TradingPlaceHomeAdapter
import datn.cnpm.rcsystem.feature.gift.detail.GiftDetailFragment
import datn.cnpm.rcsystem.feature.tradingplace.detail.TradingPlaceDetailFragment
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

/**
 * [DashboardCustomerFragment]
 */
@AndroidEntryPoint
class DashboardCustomerFragment : BaseFragment<FragmentCustomerDashboardBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCustomerDashboardBinding
        get() = FragmentCustomerDashboardBinding::inflate

    private val garbageAdapter by lazy { GarbageAdapter() }
    private val tPlaceHomeAdapter by lazy { TradingPlaceHomeAdapter() }
    private val giftAdapter by lazy { GiftYouMayBeLikeAdapter() }

    @Inject
    lateinit var authPreference: AuthPreference

    private val viewModel: DashboardCustomerViewModel by viewModels()
    override fun initData(data: Bundle?) {
        viewModel.getCustomerInfo()
        viewModel.fetchRandomTPlace()
        viewModel.fetchRandomGift()
    }

    override fun initViews() {
        coordinateMotion()
        binding.apply {
            rvGarbageTrade.adapter = garbageAdapter
            rvPlaceTrade.adapter = tPlaceHomeAdapter
            rvGift.adapter = giftAdapter
        }
    }

    override fun initActions() {

        binding.apply {
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            btnPlace.setOnClickListener {
                findNavController().navigate(R.id.tradingPlaceFragment)
            }

            btnGift.setOnClickListener {
                findNavController().navigate(R.id.giftFragment)
            }

            tvVATPlace.setOnClickListener {
                findNavController().navigate(R.id.tradingPlaceFragment)
            }

            tvVAGift.setOnClickListener {
                findNavController().navigate(R.id.giftFragment)
            }

            btnScan.setOnClickListener {
                context?.let {
                    Toast.makeText(it, "This feature is not implemented", Toast.LENGTH_SHORT).show()
                }
            }
            ivAvatar.setOnClickListener {
                findNavController().navigate(
                    R.id.personalFragment
                )
            }

            btnSetting.setOnClickListener {
                val intent = Intent(this@DashboardCustomerFragment.activity, AuthenticationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.startActivity(intent)
                activity?.finish()
                authPreference.isRememberMe = false
            }

            tPlaceHomeAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.placeDetailFragment, bundleOf(
                        Pair(
                            TradingPlaceDetailFragment.TRADING_PLACE_ID_KEY, it.id
                        ),
                        Pair(
                            TradingPlaceDetailFragment.TRADING_PLACE_AGENT_ID_KEY, it.agentId
                        )
                    )
                )
            }
            giftAdapter.onItemClick = {
                findNavController().navigate(
                    R.id.giftDetailFragment, bundleOf(
                        Pair(
                            GiftDetailFragment.GIFT_ID_KEY, it.id
                        )
                    )
                )
            }
        }
    }

    override fun initObservers() {
        garbageAdapter.submitList(
            listOf(
                RecyclingGarbage(1, R.drawable.paper),
                RecyclingGarbage(2, R.drawable.glass_bottles),
                RecyclingGarbage(3, R.drawable.food_and_beverage_container),
                RecyclingGarbage(4, R.drawable.flattened_cardboard),
                RecyclingGarbage(5, R.drawable.plastic),
                RecyclingGarbage(6, R.drawable.food_and_beverage)
            )
        )

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
                selector = { state -> state.userEntity },
                observer = { user ->
                    user?.let {
                        GlideHelper.loadImage(
                            user.avatar.orEmpty(),
                            binding.ivAvatar,
                            R.drawable.ic_person
                        )

                        binding.tvGarbageWeight.text = getString(
                            R.string.label_total_weight,
                            it.garbage?.exchange ?: 0.0
                        )
                        binding.tvPoint.text = it.point?.remainPoint.toPoint()
                        binding.tvUsername.text = it.name
                    }

                }
            )
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.tPlaceList },
                observer = { tPlaceList ->
                    tPlaceList?.let {
                        tPlaceHomeAdapter.submitList(tPlaceList)
                    }

                }
            )
            observe(
                owner = viewLifecycleOwner,
                selector = { state -> state.giftList },
                observer = { giftList ->
                    giftList?.let {
                        giftAdapter.submitList(giftList)
                    }
                }
            )
        }
    }

    private fun coordinateMotion() {
        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / binding.appBarLayout.totalScrollRange.toFloat()
            binding.motionLayout.progress = seekPosition
        }

        binding.appBarLayout.addOnOffsetChangedListener(listener)
    }
}