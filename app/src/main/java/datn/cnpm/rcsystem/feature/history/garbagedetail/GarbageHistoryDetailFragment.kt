package datn.cnpm.rcsystem.feature.history.garbagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGarbageHistoryDetailBinding
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity
import datn.cnpm.rcsystem.feature.history.garbage.GarbageHistoryFragment

/**
 * A simple [GarbageHistoryDetailFragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class GarbageHistoryDetailFragment : BaseFragment<FragmentGarbageHistoryDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGarbageHistoryDetailBinding
        get() = FragmentGarbageHistoryDetailBinding::inflate

    private val viewModel: GarbageHistoryDetailViewModel by viewModels()
    override fun initData(data: Bundle?) {
        data?.let { bundle ->
            bundle.getParcelable<GarbageUserHistoryEntity>(GarbageHistoryFragment.GARBAGE_HISTORY_KEY)?.let {
                viewModel.initData(it)
            }
        }
    }

    override fun initViews() {

    }

    override fun initActions() {
        binding.apply {
        }
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
            selector = { state -> state.garbageHistory },
            observer = { garbageHistory ->
                garbageHistory?.let {
                }
            }
        )
    }
}
