package datn.cnpm.rcsystem.feature.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentLeaderboardBinding


@AndroidEntryPoint
class LeaderboardFragment : BaseFragment<FragmentLeaderboardBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLeaderboardBinding
        get() = FragmentLeaderboardBinding::inflate
    private val adapter: StaffRankAdapter by lazy { StaffRankAdapter() }

    private val viewModel: LeaderboardViewModel by viewModels()
    override fun initData(data: Bundle?) {
        viewModel.getStatisticTopStaffCollect()
    }

    override fun initViews() {
        setStatusBarColor(R.color.white)
        binding.rvRank.adapter = adapter
        SingletonObject.staff?.let {
            binding.tvWeight.text = "${it.weightTotal}"
        }
    }

    override fun initActions() = Unit

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
            selector = { state -> state.listResult },
            observer = { listResult ->
                binding.apply {
                    listResult?.let {
                        it.firstOrNull()?.let { data ->
                            GlideHelper.loadImage(
                                data.avatarUrl.orEmpty(),
                                ivAvatarRank1,
                                R.drawable.ic_person
                            )
                            tvNameRank1.text = data.name
                        }
                        it.getOrNull(1)?.let { data ->
                            GlideHelper.loadImage(
                                data.avatarUrl.orEmpty(),
                                ivAvatarRank2,
                                R.drawable.ic_person
                            )
                            tvNameRank2.text = data.name
                        }
                        it.getOrNull(2)?.let { data ->
                            GlideHelper.loadImage(
                                data.avatarUrl.orEmpty(),
                                ivAvatarRank2,
                                R.drawable.ic_person
                            )
                            tvNameRank2.text = data.name
                            adapter.submitList(it.subList(3, it.lastIndex))
                        }
                    }
                }

            }
        )
    }
}