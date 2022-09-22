package datn.cnpm.rcsystem.feature.tradingplace

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentTradingPlaceBinding

/**
 * [TradingPlaceFragment]
 */
@AndroidEntryPoint
class TradingPlaceFragment : BaseFragment<FragmentTradingPlaceBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTradingPlaceBinding
        get() = FragmentTradingPlaceBinding::inflate

    private val  viewModel: TradingPlaceViewModel by viewModels()

    private val tPlaceAdapter: TradingPlaceAdapter by lazy { TradingPlaceAdapter() }

    override fun initData(data: Bundle?) {
        viewModel.fetchTPlaceForUser()
    }

    override fun initViews() {
        binding.rvTradingPlace.adapter = tPlaceAdapter
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
            selector = { state -> state.listPlace },
            observer = { listPlace ->
                if(listPlace.isNotEmpty()) {
                    tPlaceAdapter.submitList(listPlace)
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AAAA","onDestroy")
    }
}