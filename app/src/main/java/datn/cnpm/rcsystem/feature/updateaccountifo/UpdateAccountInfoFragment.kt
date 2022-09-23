package datn.cnpm.rcsystem.feature.updateaccountifo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.databinding.FragmentUpdateAccountInfoBinding
import java.io.File


@AndroidEntryPoint
class UpdateAccountInfoFragment : BaseFragment<FragmentUpdateAccountInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateAccountInfoBinding
        get() = FragmentUpdateAccountInfoBinding::inflate

    private val districtAndPOCAdapter: DistrictAndPOCAdapter by lazy { DistrictAndPOCAdapter() }

    private val viewModel: UpdateUserInfoViewModel by viewModels()

    private var cityList = emptyList<String>()
    private lateinit var bottomBehavior: BottomSheetBehavior<ConstraintLayout>
    override fun initData(data: Bundle?) {
        data?.let {
            viewModel.checkUserUpdated(
                data.getBoolean(IS_UPDATED_KEY),
//                data.getString(UUID_KEY, "")
            )
        }
        cityList = context?.resources?.getStringArray(R.array.City)?.toList()?: emptyList()
    }

    override fun initViews() {
        bottomBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })

        binding.rvDistrictAndPOC.adapter = districtAndPOCAdapter
    }

    override fun initActions() {

        districtAndPOCAdapter.onItemClick = { s: String, type: DistrictAndPOCAdapter.Type ->
            if (type == DistrictAndPOCAdapter.Type.ProvinceOrCity) {
                binding.tvPOC.text = s
            } else if (type == DistrictAndPOCAdapter.Type.District) {
                binding.tvDistrict.text = s
            }
            bottomBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.apply {
            btnSave.setOnClickListener {
                viewModel.updateUserInfo(
                    viewModel.avatarFile ?: genDefaultAvatarFile(),
                    tieFullName.text.toString(),
                    tiePN.text.toString(),
                    tieIN.text.toString(),
                    tieDOB.text.toString(),
                    tieStreet.text.toString(),
                    tvDistrict.text.toString(),
                    tvPOC.text.toString()
                )
            }

            tvDistrict.setOnClickListener {
                districtAndPOCAdapter.type = DistrictAndPOCAdapter.Type.District
                if(cityList.isNotEmpty()) {
                    when (tvPOC.text) {
                        cityList[0] -> {
                            districtAndPOCAdapter.submitList(context?.resources?.getStringArray(R.array.DN)?.toList()?: emptyList())
                        }
                        cityList[1] -> {
                            districtAndPOCAdapter.submitList(context?.resources?.getStringArray(R.array.HN)?.toList()?: emptyList())
                        }
                        cityList[2] -> {
                            districtAndPOCAdapter.submitList(context?.resources?.getStringArray(R.array.HCM)?.toList()?: emptyList())
                        }
                    }
                }
                bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

            tvPOC.setOnClickListener {
                districtAndPOCAdapter.type = DistrictAndPOCAdapter.Type.ProvinceOrCity
                districtAndPOCAdapter.submitList(cityList)
                bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

            ivClose.setOnClickListener {
                bottomBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }

            ivCam.setOnClickListener {
                pickImageFromGallery()
            }
        }
    }

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
            selector = { state -> state.user },
            observer = { user ->
                user?.let {

                }
            }
        )

    }

    private val pickImageFromGalleryForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    binding.ivAvatar.setImageURI(uri)
                    getRealPathFromURI(uri)?.let {
                        // File
                        viewModel.avatarFile = File(it)
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

    private fun genDefaultAvatarFile(): File {
        val path = Uri.parse("android.resource://datn.cnpm.rcsystem/" + R.drawable.ic_person)
        return File(getRealPathFromURI(path)!!)
    }

    companion object {
        const val IS_UPDATED_KEY = "IS_UPDATED_KEY"
        const val UUID_KEY = "UUID_KEY"
    }
}