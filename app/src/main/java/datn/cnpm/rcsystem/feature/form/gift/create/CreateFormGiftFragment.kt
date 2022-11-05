package datn.cnpm.rcsystem.feature.form.gift.create

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.setColor
import datn.cnpm.rcsystem.common.extension.setSpanClick
import datn.cnpm.rcsystem.common.utils.CommonUtils.toPoint
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentCreateGiftFormBinding


@AndroidEntryPoint
class CreateFormGiftFragment : BaseFragment<FragmentCreateGiftFormBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateGiftFormBinding
        get() = FragmentCreateGiftFormBinding::inflate

    val viewModel: CreateFormGiftViewModel by viewModels()

    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.setGift(
                it.getParcelable(GIFT_KEY)
            )
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.redeem_gift_label), R.drawable.ic_back)
        SingletonObject.customer?.let {
            binding.apply {
                tvUserNameAndPhone.text =
                    getString(R.string.create_form_name_and_phone, it.name, it.phoneNumber)
                tvAddress.text = it.address?.street
                tvTermOfTransaction.setColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue_1c75ff
                    ),
                    getString(R.string.create_form_gift_term_transaction_hyper_link_text)
                )
                tvTermOfTransaction.setSpanClick(
                    getString(R.string.create_form_gift_term_transaction_hyper_link_text),
                    false
                ) {
                    //TODO: Handle click later
                }
                tvGiftDescription.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    override fun initActions() {
        binding.apply {
            btnOrder.setOnClickListener {
                SingletonObject.customer?.address?.let {
                    viewModel.createTransportGarbage(it.street, it.district, it.provinceOrCity)
                }
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
                is CreateFormGiftEvent.CreateTransportGiftSuccess -> {
                    context?.let {
                        showDialogConfirm(
                            "You created transport garbage form successfully. Our staff will go soon.",
                            onConfirmClick = {
                                findNavController().popBackStack(R.id.dashboardCustomerFragment, true)
                            })
                    }
                }
                is CreateFormGiftEvent.CreateTransportGiftFailure -> {
                    showError(event.error)
                }
            }
        }

        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.gift },
            observer = { gift ->
                gift?.let { data ->
                    binding.apply {
                        tvBrand.text = data.brand
                        tvGiftName.text = data.name
                        tvGiftDescription.text = data.description
                        tvAddress.text = "${data.street}, ${data.district}, ${data.provinceOrCity}"
                        tvTotalOrderValue.text = data.redemptionPoint.toPoint()
                        tvPoint.text = data.redemptionPoint.toPoint()

                        GlideHelper.loadImage(
                            data.url ?: "",
                            imgGift,
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
