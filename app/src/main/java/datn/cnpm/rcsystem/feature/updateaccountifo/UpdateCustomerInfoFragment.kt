package datn.cnpm.rcsystem.feature.updateaccountifo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseFragment
import datn.cnpm.rcsystem.common.extension.gone
import datn.cnpm.rcsystem.common.utils.glide.GlideHelper
import datn.cnpm.rcsystem.data.entitiy.Role
import datn.cnpm.rcsystem.databinding.FragmentUpdateAccountInfoBinding
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import javax.inject.Inject


@AndroidEntryPoint
class UpdateCustomerInfoFragment : BaseFragment<FragmentUpdateAccountInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateAccountInfoBinding
        get() = FragmentUpdateAccountInfoBinding::inflate

    private val viewModel: UpdateUserInfoViewModel by viewModels()
    private val provinceOrCityDialog: ProvinceAndDistrictDialog by lazy {
        ProvinceAndDistrictDialog.newInstance(DistrictAndPOCAdapter.Type.ProvinceOrCity.ordinal)
    }
    private val districtDialog: ProvinceAndDistrictDialog by lazy {
        ProvinceAndDistrictDialog.newInstance(DistrictAndPOCAdapter.Type.District.ordinal,
            binding.tvPOC.text.toString())
    }

    private var cityList = emptyList<String>()

    @Inject
    lateinit var authPreference: AuthPreference
    override fun isDisableFullScreen(): Boolean = false

    override fun initData(data: Bundle?) {
        cityList = context?.resources?.getStringArray(R.array.City)?.toList() ?: emptyList()
    }

    override fun initViews() {
        if (authPreference.role == Role.STAFF.name) {
            binding.apply {
                SingletonObject.staff?.let { data ->
                    edtName.setText(data.name)
                    edtName.isEnabled = false
                    edtDOB.isEnabled = false

                    data.dayOfBirth?.let {
                        edtDOB.setText(SimpleDateFormat("dd/MM/yyyy").format(it))
                    }
                    edtPN.setText(data.phoneNumber)
                    edtPN.isEnabled = false
                    edtStreet.setText(data.address?.street)
                    edtStreet.isEnabled = false
                    tvDistrict.text = data.address?.district
                    tvPOC.text = data.address?.provinceOrCity
                    edtIN.setText(data.identityCardNumber)
                    edtIN.isEnabled = false
                    GlideHelper.loadImage(
                        data.avatar,
                        ivAvatar,
                        R.drawable.ic_person
                    )
                }
                btnSave.gone()
            }
        } else if (authPreference.role == Role.CUSTOMER.name) {
            binding.apply {
                SingletonObject.customer?.let { data ->
                    edtName.setText(data.name)
                    data.dOB?.let {
                        edtDOB.setText(SimpleDateFormat("dd/MM/yyyy").format(it))
                    }
                    edtPN.setText(data.phoneNumber)
                    edtStreet.setText(data.address?.street)
                    tvDistrict.text = data.address?.district
                    tvPOC.text = data.address?.provinceOrCity
                    edtIN.setText(data.identityCardNumber)
                    GlideHelper.loadImage(
                        data.avatar ?: "",
                        ivAvatar,
                        R.drawable.ic_person
                    )
                }
            }
        }
    }

    override fun initActions() {
        if (authPreference.role == Role.CUSTOMER.name) {
            provinceOrCityDialog.onItemClicked = { data, _ ->
                binding.tvPOC.run {
                    if (text != data) {
                        reselectDistrict(data)
                    }
                }
                binding.tvPOC.text = data
            }

            districtDialog.onItemClicked = { data, _ ->
                binding.tvDistrict.text = data
            }

            binding.apply {
                btnSave.setOnClickListener {
                    viewModel.updateUserInfo(
                        viewModel.avatarFile ?: genDefaultAvatarFile(),
                        edtName.text.toString(),
                        edtPN.text.toString(),
                        edtIN.text.toString(),
                        edtDOB.text.toString(),
                        edtStreet.text.toString(),
                        tvDistrict.text.toString(),
                        tvPOC.text.toString()
                    )
                }

                tvDistrict.setOnClickListener {
                    districtDialog.show(childFragmentManager,
                        ProvinceAndDistrictDialog::class.simpleName)

                }

                tvPOC.setOnClickListener {
                    provinceOrCityDialog.show(childFragmentManager,
                        ProvinceAndDistrictDialog::class.simpleName)
                }

                ivCam.setOnClickListener {
                    pickImageFromGallery()
                }
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
                lifecycleScope.launch {
                    uri?.let {
                        getRealPathFromURI(uri)?.let {
                            // File
                            viewModel.avatarFile = Compressor.compress(requireContext(), File(it))
                        }
                    }
                }
            }
        }

    private fun pickImageFromGallery() {
        val pickIntent = Intent(MediaStore.ACTION_PICK_IMAGES)
        pickImageFromGalleryForResult.launch(pickIntent)
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

    private fun reselectDistrict(city: String) {
        binding.tvDistrict.text = when (city) {
            cityList[0] -> {
                    context?.resources?.getStringArray(R.array.DN)?.toList()?.first()
            }
            cityList[1] -> {
                context?.resources?.getStringArray(R.array.HN)?.toList()?.first()
            }
            cityList[2] -> {
                context?.resources?.getStringArray(R.array.HCM)?.toList()?.first()
            }
            else -> ""
        }
    }

    companion object {
        const val IS_UPDATED_KEY = "IS_UPDATED_KEY"
        const val ID_KEY = "ID_KEY"
    }
}