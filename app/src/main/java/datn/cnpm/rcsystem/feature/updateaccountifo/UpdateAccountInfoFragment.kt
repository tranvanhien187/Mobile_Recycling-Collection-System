package datn.cnpm.rcsystem.feature.updateaccountifo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentUpdateAccountInfoBinding

@AndroidEntryPoint
class UpdateAccountInfoFragment: BaseFragment<FragmentUpdateAccountInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateAccountInfoBinding
        get() = FragmentUpdateAccountInfoBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
    }

    override fun initObservers() {
    }
}