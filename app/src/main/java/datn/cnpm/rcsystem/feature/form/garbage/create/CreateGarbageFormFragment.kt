package datn.cnpm.rcsystem.feature.form.garbage.create

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
import datn.cnpm.rcsystem.databinding.FragmentCreateGarbageFormBinding

@AndroidEntryPoint
class CreateGarbageFormFragment : BaseFragment<FragmentCreateGarbageFormBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateGarbageFormBinding
        get() = FragmentCreateGarbageFormBinding::inflate

    val viewModel: CreateGarbageFormViewModel by viewModels()

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        showToolbar(getString(R.string.exchange_garbage_label), R.drawable.ic_back)

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
        binding.apply {
            btnSubmit.setOnClickListener {
                viewModel.createTransportGarbage(
                    tvWeight.text.toString().toFloat(),
                    tvStreet.text.toString(),
                    tvDistrict.text.toString(),
                    tvCity.text.toString(),
                )
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
                is CreateGarbageFormEvent.CreateTransportGarbageSuccess -> {
                    context?.let {
                        showDialogConfirm(
                            "You created transport garbage form successfully. Out staff will go soon.",
                            onConfirmClick = {
                                findNavController().popBackStack(R.id.dashboardCustomerFragment, true)
                            })
                    }
                }
            }
        }
    }
}
