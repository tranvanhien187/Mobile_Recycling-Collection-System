package datn.cnpm.rcsystem.feature.form

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentCreateFormBinding
import datn.cnpm.rcsystem.databinding.FragmentGiftDetailBinding

@AndroidEntryPoint
class CreateFormFragment : BaseFragment<FragmentCreateFormBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateFormBinding
        get() = FragmentCreateFormBinding::inflate

    companion object {

    }

    override fun initData(data: Bundle?) {

    }

    override fun initViews() {
        SingletonObject.customer?.let {
            binding.apply {
                tvName.text = it.name
                tvPhoneNumber.text = it.phoneNumber
                tvStreet.setText(it.address?.street)
                tvDistrict.setText(it.address?.district)
                tvCity.setText(it.address?.provinceOrCity)
            }
        }
    }

    override fun initActions() {
    }

    @SuppressLint("SetTextI18n")
    override fun initObservers() {
//        viewModel.observe(
//            owner = viewLifecycleOwner,
//            selector = { state -> state.loading },
//            observer = { loading ->
//                if (loading) {
//                    showLoading()
//                } else {
//                    hideLoading()
//                }
//            }
//        )
//
//        viewModel.observe(
//            owner = viewLifecycleOwner,
//            selector = { state -> state.gift },
//            observer = { gift ->
//                gift?.let {
//                    binding.apply {
//                        tvName.text = it.name
//                        tvBrand.text = it.brand
//                        tvPhoneNumber.text = it.agentPhone
//                        tvAgentName.text = it.agentName
//                        tvPoint.text = "${it.redemptionPoint}"
//                        tvAddress.text = "${it.street}, ${it.district}, ${it.provinceOrCity}"
//                        GlideHelper.loadImage(
//                            it.url ?: "",
//                            imgBanner,
//                            R.drawable.image_default_image_rectangle
//                        )
//                    }
//                }
//            }
//        )
    }
}
