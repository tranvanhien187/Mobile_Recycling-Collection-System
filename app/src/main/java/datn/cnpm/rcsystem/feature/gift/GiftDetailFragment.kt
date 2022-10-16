package datn.cnpm.rcsystem.feature.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentGiftDetailBinding

class GiftDetailFragment : BaseFragment<FragmentGiftDetailBinding>(){

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGiftDetailBinding
        get() = FragmentGiftDetailBinding::inflate

    override fun initData(data: Bundle?) {
        //TODO("Not yet implemented")
    }

    override fun initViews() {
        //TODO("Not yet implemented")
    }

    override fun initActions() {
        //TODO("Not yet implemented")
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }
}
