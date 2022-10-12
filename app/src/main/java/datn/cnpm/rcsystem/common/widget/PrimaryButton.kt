package datn.cnpm.rcsystem.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import datn.cnpm.rcsystem.R

class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet,
    defStyle: Int = 0,
) : AppCompatButton(context, attributes, defStyle) {

    companion object {
        private const val DEFAULT_TEXT_SIZE = 16F
    }

    init {
        background = ContextCompat.getDrawable(context, R.drawable.bg_primary_button_enable)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        typeface = ResourcesCompat.getFont(context, R.font.proximanova_bold)
        isAllCaps = false
        gravity = Gravity.CENTER
        textSize = DEFAULT_TEXT_SIZE
    }
}
