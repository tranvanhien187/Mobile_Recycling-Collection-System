package datn.cnpm.rcsystem.feature.home.dealer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentHomeDealerBinding

@AndroidEntryPoint
class HomeDealerFragment : BaseFragment<FragmentHomeDealerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeDealerBinding
        get() = FragmentHomeDealerBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
    }

    override fun initObservers() {
    }
}