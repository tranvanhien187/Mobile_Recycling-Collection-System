package datn.cnpm.rcsystem.common.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.databinding.CustomDialogConfirmBinding

class ConfirmDialog(private val titleText: String, private val drawableId: Int, private val onConfirmClick: () -> Unit) :
    DialogFragment() {

    private lateinit var binding: CustomDialogConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomDialogConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            context.resources.displayMetrics.run {
                val width = widthPixels - (widthPixels / 10)
                setLayout(width, width)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tvTitle.text = titleText
        binding.btnOk.setOnClickListener {
            onConfirmClick.invoke()
            this.dismiss()
        }
        binding.ivStatus.setImageResource(drawableId)
    }
}