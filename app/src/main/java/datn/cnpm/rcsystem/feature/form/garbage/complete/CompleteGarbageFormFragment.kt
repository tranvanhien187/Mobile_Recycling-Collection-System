package datn.cnpm.rcsystem.feature.form.garbage.complete

import android.annotation.SuppressLint
import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentCompleteGarbageFormBinding
import java.io.File

@AndroidEntryPoint
class CompleteGarbageFormFragment : BaseFragment<FragmentCompleteGarbageFormBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompleteGarbageFormBinding
        get() = FragmentCompleteGarbageFormBinding::inflate

    val viewModel: CompleteGarbageFormViewModel by viewModels()

    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.setForm(
                it.getParcelable(FORM_KEY)
            )
        }
    }

    override fun initViews() {
        showToolbar(getString(R.string.complete_form_label), R.drawable.ic_back)
    }

    @SuppressLint("SetTextI18n")
    override fun initActions() {
        binding.run {
            btnComplete.setOnClickListener {
                viewModel.completeForm(tvWeight.text.toString().replace(",",".").replace(" Kg",""), tvHistoryId.text.toString())
            }
            ivEvidence.setOnClickListener {
                pickImageFromGallery()
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
                is CompleteGarbageFormEvent.EvidenceEmpty -> {
                    showError("Evidence is not empty.")
                }
                is CompleteGarbageFormEvent.CompleteTransportGarbageSuccess -> {
                    showDialogConfirm(
                        "You completed transport garbage form success. Keep going !",
                        onConfirmClick = {
                            findNavController().popBackStack()
                        })
                }
            }
        }

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
                        tvHistoryId.text = form.id
                        tvWeight.setText("${form.weight} Kg")
                        tvName.text = form.customerName
                        tvAddress.text =
                            "${form.street}, ${form.district}, ${form.cityOrProvince}"
                    }
                }
            }
        )

        viewModel.observe(
            owner = viewLifecycleOwner,
            selector = { state -> state.file },
            observer = { file ->
                file?.let {
                    binding.ivEvidence.setImageURI(file.toUri())
                }
            }
        )
    }

    private val pickImageFromGalleryForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    getRealPathFromURI(uri)?.let {
                        // File
                        viewModel.setFileEvidence(File(it))
                    }
                }
            }
        }

    private fun pickImageFromGallery() {
//        val pickIntent = Intent(MediaStore.ACTION_PICK_IMAGES)
//        pickImageFromGalleryForResult.launch(pickIntent)
    }

    @SuppressLint("Recycle")
    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor: Cursor? = requireActivity().contentResolver.query(uri, null, null, null, null)
        return cursor?.let {
            it.moveToFirst()
            val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return it.getString(idx)
        } ?: kotlin.run {
            return ""
        }
    }

    companion object {
        const val FORM_KEY = "FORM_KEY"
    }
}
