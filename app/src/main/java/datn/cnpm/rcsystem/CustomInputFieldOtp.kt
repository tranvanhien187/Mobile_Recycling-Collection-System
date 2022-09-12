package datn.cnpm.rcsystem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import datn.cnpm.rcsystem.CustomInputFieldOtp.Companion.OTP_INPUT_NUMBER
import datn.cnpm.rcsystem.databinding.ItemOtpInputBinding

/**
 * This class customized for OTP/PIN input field component
 */
class CustomInputFieldOtp : FrameLayout {

    companion object {
        const val OTP_INPUT_NUMBER = 6
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initByAttribute()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initByAttribute()
    }

    var recyclerViewOtp: RecyclerView? = null
        private set

    var textviewError: TextView? = null
        private set

    var linearLayoutError: LinearLayout? = null
        private set

    var otpAdapter: OtpAdapter? = null
        private set

    private var otpList = listOf<Otp>()
    var onFinishInput: (() -> Unit)? = null
    var onFinishEachInput: ((index: Int, value: Int) -> Unit)? = null

    private fun initByAttribute() {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_input_field_otp, this, true)
        initView(view)
        initOtpAdapter()
    }

    private fun initView(view: View?) {
        // get view
        if (view != null) {
            recyclerViewOtp = view.findViewById(R.id.rv_otp_input_fields)
            textviewError = view.findViewById(R.id.tv_error)
            linearLayoutError = view.findViewById(R.id.ll_error_layout)
        }
    }

    /**
     * Init adapter for otp input list
     */
    private fun initOtpAdapter() {
        // set Adapter
        otpAdapter = OtpAdapter(
            onEachInputCompleted = { index: Int, value: Int -> onEachInputCompleted(index, value) },
            onInputFinished = { onInputFinished() }
        )
        otpList = List(OTP_INPUT_NUMBER) { index -> Otp(index = index, value = null) }.toList()
        otpAdapter?.submitList(otpList)
        recyclerViewOtp?.apply {
            adapter = otpAdapter
            layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
                override fun canScrollHorizontally(): Boolean {
                    return false
                }
            }
        }
    }

    /**
     * Set each input onCompleted event
     */
    private fun onEachInputCompleted(index: Int, value: Int) {

        onFinishEachInput?.invoke(index, value)

        val inputNumber = OTP_INPUT_NUMBER

        if (index < inputNumber) {
            otpList[index].value = value
            recyclerViewOtp?.layoutManager?.findViewByPosition(index + 1)?.requestFocus()
        }

    }

    /**
     * Set onCompleted event for whole input list
     */
    private fun onInputFinished() {
        onFinishInput?.invoke()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    fun getCurrentInput(): String {
        var str = ""
        otpList.forEach { otp ->
            if (otp.value != null) str += otp.value.toString()
        }
        return str
    }

}

/**
 * This class is defined for each otp input
 */
data class Otp(val index: Int, var value: Int?)

/**
 * This class is defined for [Otp] DiffUtil
 */
class OtpDiffUtil : DiffUtil.ItemCallback<Otp>() {
    override fun areItemsTheSame(oldItem: Otp, newItem: Otp): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: Otp, newItem: Otp): Boolean {
        return oldItem == newItem
    }
}

/**
 * Adapter for [Otp] input list
 *
 * @property onEachInputCompleted Callback after user input a number
 * @property onInputFinished Callback after user finish otp
 */
class OtpAdapter(
    private val onEachInputCompleted: ((Int, Int) -> Unit)?,
    private val onInputFinished: (() -> Unit)?
) : ListAdapter<Otp, RecyclerView.ViewHolder>(OtpDiffUtil()) {

    /**
     * View Holder for an OTP input number
     *
     * @property otpBinding the [ItemOtpInputBinding]
     * @property onEachInputCompleted Callback after user input a number
     * @property onInputFinished Callback after user finish otp
     */
    class OtpViewHolder(
        private val otpBinding: ItemOtpInputBinding,
        private val onEachInputCompleted: ((Int, Int) -> Unit)?,
        private val onInputFinished: (() -> Unit)?
    ) : RecyclerView.ViewHolder(otpBinding.root) {

        private var otp: Otp? = null

        init {
            otpBinding.edtOtp.doOnTextChanged { text, start, _, _ ->
                // case: input is empty
                if (text?.length == 1) {
                    onEachInputCompleted?.invoke(
                        otp?.index ?: 0,
                        text.toString().toIntOrNull() ?: 0
                    )
                }
                // case: modify
                if (text?.length == 2) {
                    val modifiedInput = when (start) {
                        1 -> text.toString()[1] // text cursor after number
                        0 -> text.toString()[0] // text cursor before number
                        else -> null
                    }
                    otpBinding.edtOtp.setText(
                        modifiedInput.toString(),
                        TextView.BufferType.EDITABLE
                    )
                }
                // case: last input
                if (otp?.index == OTP_INPUT_NUMBER - 1) {
                    otpBinding.edtOtp.clearFocus()
                    onInputFinished?.invoke()
                }

            }

        }

        fun bind(otp: Otp) {
            this.otp = otp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OtpViewHolder(
            ItemOtpInputBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onEachInputCompleted, onInputFinished
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? OtpViewHolder)?.bind(getItem(position))
    }

}