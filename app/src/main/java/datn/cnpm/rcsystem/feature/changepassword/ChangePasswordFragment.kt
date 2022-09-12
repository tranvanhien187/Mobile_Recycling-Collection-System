package datn.cnpm.rcsystem.feature.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentChangePasswordBinding

/**
 * [ChangePasswordFragment]
 */
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChangePasswordBinding
        get() = FragmentChangePasswordBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
    }

    override fun initObservers() {

    }
}