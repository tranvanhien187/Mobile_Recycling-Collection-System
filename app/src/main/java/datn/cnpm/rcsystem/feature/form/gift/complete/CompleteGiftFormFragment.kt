package datn.cnpm.rcsystem.feature.form.gift.complete

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
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
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.databinding.FragmentCompleteGiftFormBinding
import java.io.File

@AndroidEntryPoint
class CompleteGiftFormFragment : BaseFragment<FragmentCompleteGiftFormBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompleteGiftFormBinding
        get() = FragmentCompleteGiftFormBinding::inflate

    val viewModel: CompleteGiftFormViewModel by viewModels()

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
                viewModel.completeForm(tvHistoryId.text.toString())
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
                is CompleteGiftFormEvent.EvidenceEmpty -> {
                    showError("Evidence is not empty.")
                }
                is CompleteGiftFormEvent.CompleteTransportGarbageSuccess -> {
                    showDialogConfirm(
                        "You completed transport gift form success. Keep going !",
                        onConfirmClick = {
                            findNavController().popBackStack(R.id.dashboardStaffFragment, true)
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
                form?.let { data ->
                    binding.apply {
                        tvHistoryId.text = data.id
                        tvGiftBrand.text = data.giftBrand
                        tvGiftName.text = data.giftName
                        tvName.text = data.customerName
                        tvAddress.text =
                            "${data.street}, ${data.district}, ${data.cityOrProvince}"

                        GlideHelper.loadImage(
                            data.giftUrl ?: "",
                            ivGift,
                            R.drawable.image_default_image_rectangle
                        )
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
        if (Build.VERSION.SDK_INT >= 33) {
//            val pickIntent = Intent(MediaStore.ACTION_PICK_IMAGES)
//            pickImageFromGalleryForResult.launch(pickIntent)
        }
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