package datn.cnpm.rcsystem.feature.tradingplace

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentTradingPlaceBinding

/**
 * [TradingPlaceFragment]
 */
@AndroidEntryPoint
class TradingPlaceFragment : BaseFragment<FragmentTradingPlaceBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTradingPlaceBinding
        get() = FragmentTradingPlaceBinding::inflate

    private val viewModel: TradingPlaceViewModel by viewModels()

    private val tPlaceAdapter: TradingPlaceAdapter by lazy { TradingPlaceAdapter() }

    override fun initData(data: Bundle?) {
        viewModel.fetchTPlaceForUser()
    }

    override fun initViews() {
        showToolbar(getString(R.string.trading_place_label), R.drawable.ic_back)
        binding.rvTradingPlace.adapter = tPlaceAdapter

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.filter,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.spinnerCategory.adapter = adapter
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
            selector = { state -> state.listPlace },
            observer = { listPlace ->
                if (listPlace.isNotEmpty()) {
                    tPlaceAdapter.submitList(listPlace)
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AAAA", "onDestroy")
    }
}