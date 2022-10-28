package datn.cnpm.rcsystem.feature.form.gift.create

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentCreateFormGiftBinding

@AndroidEntryPoint
class CreateFormGiftFragment : BaseFragment<FragmentCreateFormGiftBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateFormGiftBinding
        get() = FragmentCreateFormGiftBinding::inflate

    val viewModel: CreateFormGiftViewModel by viewModels()

    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.setGift(
                it.getParcelable(GIFT_KEY)
            )
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.create_form_label), R.drawable.ic_back)

        SingletonObject.customer?.let {
            binding.apply {
                tvName.text = it.name
                tvPhoneNumber.text = it.phoneNumber
                edtStreet.setText(it.address?.street)
                edtDistrict.setText(it.address?.district)
                edtCity.setText(it.address?.provinceOrCity)
            }
        }
    }

    override fun initActions() {
        binding.apply {
            btnRedeem.setOnClickListener {
//                viewModel.createTransportGarbage(
//                    tvWeight.text.toString().toFloat(),
//                    tvStreet.text.toString(),
//                    tvDistrict.text.toString(),
//                    tvCity.text.toString(),
//                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
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

        viewModel.liveEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is CreateFormGiftEvent.CreateTransportGarbageSuccess -> {
                    context?.let {
                        showDialogConfirm(
                            "You created transport garbage form successfully. Out staff will go soon.",
                            onConfirmClick = {
                                findNavController().popBackStack()
                            })
                    }
                }
            }
        }

        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.gift },
            observer = { gift ->
                gift?.let {
                    binding.apply {
                        tvGiftName.text = it.name
                        tvTPlaceName.text = it.placeName
                        tvGiftPoint.text = it.redemptionPoint.toPoint()

                        GlideHelper.loadImage(
                            it.url ?: "",
                            ivGift,
                            R.drawable.image_default_image_rectangle
                        )

                        GlideHelper.loadImage(
                            it.url ?: "",
                            ivTPlaceBanner,
                            R.drawable.image_default_image_rectangle
                        )
                    }
                }
            }
        )
    }

    companion object {
        const val GIFT_KEY = "GIFT_KEY"
    }
}
