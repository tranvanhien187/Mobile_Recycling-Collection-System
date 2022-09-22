package datn.cnpm.rcsystem.common.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import datn.cnpm.rcsystem.databinding.CustomDialogErrorBinding


class ErrorDialog(private val errorText: String) : DialogFragment() {

    private lateinit var binding: CustomDialogErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomDialogErrorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
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

        binding.tvError.text = errorText
    }
}