package datn.cnpm.rcsystem.feature.form.gift.receive

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentReceiveGiftFormBinding
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import datn.cnpm.rcsystem.feature.transportform.garbage.GarbageFormFragment

@AndroidEntryPoint
class ReceiveFromGiftFragment : BaseFragment<FragmentReceiveGiftFormBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReceiveGiftFormBinding
        get() = FragmentReceiveGiftFormBinding::inflate


    private val viewModel: ReceiveFromGiftViewModel by viewModels()

    override fun isDisableFullScreen() = false

    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.getTransportFormDetail(
                it.getParcelable(GarbageFormFragment.FORM_KEY)
            )
        }
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.btnReceive.setOnClickListener {
            viewModel.receiveTransportForm()
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
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
            selector = { state -> state.form },
            observer = { form ->
                form?.let {
                    binding.apply {
                        tvGiftName.text = form.giftName
                        tvGiftBrand.text = form.giftBrand
                        tvTransportId.text = form.id
                        tvCustomerName.text = form.customerName
                        tvPhoneNumber.text = form.customerPhoneNumber
                        tvGiftDescription.text = form.description
                        tvAddress.text = "${form.street}, ${form.district}, ${form.cityOrProvince}"
                        btnReceive.isEnabled = HistoryStatus.CREATE.name == form.status
                        GlideHelper.loadImage(
                            form.giftUrl ?: "",
                            ivGift,
                            R.drawable.image_default_image_rectangle
                        )
                    }
                }
            }
        )

        viewModel.liveEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ReceiveFromGiftEvent.ReceiveFormSuccess -> {
                    binding.btnReceive.isEnabled = false
                    showDialogConfirm(
                        "You received transport garbage form success",
                        onConfirmClick = {
                            findNavController().apply {
                                navigate(R.id.dashboardStaffFragment)
                                backQueue.clear()
                            }
                        })
                }
                is ReceiveFromGiftEvent.ReceiveFormFailure -> {
                    when (event.error) {
                        ErrorCode.FORM_RESOLVED -> {
                            showError(event.error.value)
                            binding.btnReceive.isEnabled = false
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}