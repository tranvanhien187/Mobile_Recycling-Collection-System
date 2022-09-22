package datn.cnpm.rcsystem.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.basesource.common.utils.glide.GlideHelper
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentUserDashboardBinding
import datn.cnpm.rcsystem.feature.dashboard.adapter.GarbageAdapter
import datn.cnpm.rcsystem.feature.dashboard.adapter.TradingPlaceHomeAdapter

/**
 * [DashboardFragment]
 */
@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentUserDashboardBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserDashboardBinding
        get() = FragmentUserDashboardBinding::inflate

    private val garbageAdapter by lazy { GarbageAdapter() }
    private val tPlaceHomeAdapter by lazy { TradingPlaceHomeAdapter() }

    private val viewModel: DashboardViewModel by viewModels()
    override fun initData(data: Bundle?) {
        viewModel.getUserInfo()
        viewModel.fetchRandomTPlace()
    }

    override fun initViews() {

    }

    override fun initActions() {
        binding.rvGarbageTrade.adapter = garbageAdapter
        binding.rvPlaceTrade.adapter = tPlaceHomeAdapter
    }

    override fun initObservers() {
        garbageAdapter.submitList(
            listOf(
                RecyclingGarbage(1, R.drawable.paper),
                RecyclingGarbage(2, R.drawable.food_and_beverage),
                RecyclingGarbage(3, R.drawable.food_and_beverage_container),
                RecyclingGarbage(4, R.drawable.flattened_cardboard),
                RecyclingGarbage(5, R.drawable.plastic),
                RecyclingGarbage(6, R.drawable.glass_bottles)
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
                            it.garbage?.exchange?:0.0
                        )
                        binding.tvPoint.text = it.getPoint()
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
        }
    }
}