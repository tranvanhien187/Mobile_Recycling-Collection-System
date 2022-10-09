package datn.cnpm.rcsystem.feature.transportform.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.databinding.FragmentTransportFormDetailBinding
import datn.cnpm.rcsystem.domain.model.GarbageHistoryStatus
import datn.cnpm.rcsystem.feature.transportform.list.TransportFormFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * [TransportFormDetailFragment]
 */
@AndroidEntryPoint
class TransportFormDetailFragment : BaseFragment<FragmentTransportFormDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTransportFormDetailBinding
        get() = FragmentTransportFormDetailBinding::inflate


    private val viewModel: TransportFormDetailViewModel by viewModels()


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
                            tvDateTime.text = SimpleDateFormat("dd MMM yyyy, HH:mm aa",Locale.UK).format(it)
                        }
                        tvCustomerName.text = form.customerName
                        tvPhoneNumber.text = form.customerPhoneNumber
                        tvAddress.text = "${form.street}, ${form.district}, ${form.cityOrProvince}"
                        btnReceive.isEnabled = GarbageHistoryStatus.CREATE.name == form.status
                    }
                }
            }
        )

        viewModel.liveEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is TransportFormDetailEvent.ReceiveFormSuccess -> {
                    context?.let {
                        Toast.makeText(
                            it,
                            "You received transport garbage form success",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is TransportFormDetailEvent.ReceiveFormFailure -> {
                    when (event.error) {
                        ErrorCode.FORM_RESOLVED -> {
                            context?.let {
                                Toast.makeText(
                                    it,
                                    event.error.value,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            binding.btnReceive.isEnabled = false
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}