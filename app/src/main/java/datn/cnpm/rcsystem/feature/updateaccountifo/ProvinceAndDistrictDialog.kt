package datn.cnpm.rcsystem.feature.updateaccountifo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.databinding.DialogProvinceAndDistrictBinding

class ProvinceAndDistrictDialog : BottomSheetDialogFragment() {

    private val adapter: DistrictAndPOCAdapter by lazy { DistrictAndPOCAdapter() }
    private val cityList: List<String> by lazy {
        context?.resources?.getStringArray(R.array.City)?.toList().orEmpty()
    }
    var onItemClicked: (data: String, type: DistrictAndPOCAdapter.Type) -> Unit = { _, _ -> }
    private var _binding: DialogProvinceAndDistrictBinding? = null
    private val binding get() = _binding!!
    var onShowDialog: () -> Unit = {}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        _binding = DialogProvinceAndDistrictBinding.inflate(LayoutInflater.from(context))
        dialog.setOnShowListener {
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            (it as? BottomSheetDialog)?.setContentView(binding.root)
            initDialog()
            initActions()
            onShowDialog()
        }
        return dialog
    }

    fun initActions() {
        binding.ivClose.setOnClickListener {
            dismissAllowingStateLoss()
        }
        adapter.onItemClick = { s: String, type: DistrictAndPOCAdapter.Type ->
            onItemClicked.invoke(s, type)
            dismissAllowingStateLoss()
        }
    }

    private fun showCity() {
        adapter.type = DistrictAndPOCAdapter.Type.ProvinceOrCity
        adapter.submitList(cityList)
    }

    fun showDistrict(city: String) {
        adapter.type = DistrictAndPOCAdapter.Type.District
        when (city) {
            cityList[0] -> {
                adapter.submitList(
                    context?.resources?.getStringArray(R.array.DN)?.toList().orEmpty()
                )
            }
            cityList[1] -> {
                adapter.submitList(
                    context?.resources?.getStringArray(R.array.HN)?.toList().orEmpty()
                )
            }
            cityList[2] -> {
                adapter.submitList(
                    context?.resources?.getStringArray(R.array.HCM)?.toList().orEmpty()
                )
            }
        }
    }

    private fun initDialog() {
        isCancelable = true
        binding.rvDistrictAndPOC.adapter = adapter
        arguments?.run {
            if (getInt(KEY_TYPE, 0) == DistrictAndPOCAdapter.Type.ProvinceOrCity.ordinal) {
                showCity()
            }
        }
    }

    companion object {
        private const val KEY_TYPE = "key_type"
        fun newInstance(type: Int) = ProvinceAndDistrictDialog().apply {
            arguments = bundleOf(KEY_TYPE to type)
        }
    }
}
