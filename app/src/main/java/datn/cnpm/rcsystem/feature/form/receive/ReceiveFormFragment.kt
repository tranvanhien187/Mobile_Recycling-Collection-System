package datn.cnpm.rcsystem.feature.form.receive

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.databinding.FragmentReceiveFormBinding
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import datn.cnpm.rcsystem.feature.transportform.list.TransportFormFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * [ReceiveFormFragment]
 */
@AndroidEntryPoint
class ReceiveFormFragment : BaseFragment<FragmentReceiveFormBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReceiveFormBinding
        get() = FragmentReceiveFormBinding::inflate


    private val viewModel: ReceiveFormViewModel by viewModels()


    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.getTransportFormDetail(
                it.getParcelable(TransportFormFragment.FORM_KEY)
            )
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.transport_form_detail_label), R.drawable.ic_back)
    }

    override fun initActions() {
        binding.btnReceive.setOnClickListener {
            viewModel.receiveTransportForm()
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
                        tvWeight.text = "${form.weight} Kgs"
                        tvTransportId.text = form.id
                        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(form.createAt)?.let {
                            tvDateTime.text =
                                SimpleDateFormat("dd MMM yyyy, HH:mm aa", Locale.UK).format(it)
                        }
                        tvCustomerName.text = form.customerName
                        tvPhoneNumber.text = form.customerPhoneNumber
                        tvAddress.text = "${form.street}, ${form.district}, ${form.cityOrProvince}"
                        btnReceive.isEnabled = HistoryStatus.CREATE.name == form.status
                    }
                }
            }
        )

        viewModel.liveEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ReceiveFormEvent.ReceiveFormSuccess -> {
                    binding.btnReceive.isEnabled = false
                    showDialogConfirm(
                        "You received transport garbage form success",
                        onConfirmClick = {

                        })
                }
                is ReceiveFormEvent.ReceiveFormFailure -> {
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