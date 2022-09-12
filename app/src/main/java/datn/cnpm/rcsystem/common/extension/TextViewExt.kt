package datn.cnpm.rcsystem.common.extension

import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.text.set
import androidx.core.text.toSpannable
import datn.cnpm.rcsystem.R

/**
 * This TextView Extension.
 * @param color Int the text color to change.
 * @param subString String the content of the text to change color.
 */
fun TextView.setColor(color: Int, subString: String) {
    val dataString = text
    if (dataString.isNullOrEmpty() && subString.isBlank()) return
    val statIndex = dataString.indexOf(subString)
    val endIndex = statIndex + subString.length
    if (statIndex == -1) return

    val span = SpannableString(dataString)
    val foregroundColorSpan = ForegroundColorSpan(color)
    span.setSpan(foregroundColorSpan, statIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    text = span
}

/**
 * This TextView Extension.
 * @param subStringClick String the content of the text to click.
 * @param underlineText Boolean the underline text.
 * @param onClickListener OnClickListener the action when click subString .
 */
fun TextView.setSpanClick(
    subStringClick: String,
    underlineText: Boolean,
    onClickListener: View.OnClickListener
) {
    if (text.isNullOrEmpty() && subStringClick.isBlank()) return

    val dataString = text.toSpannable()
    val statIndex = dataString.indexOf(subStringClick)
    val endIndex = statIndex + subStringClick.length
    if (statIndex == -1) return

    dataString[statIndex..endIndex] = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClickListener.onClick(widget)
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = underlineText
        }
    }

    movementMethod = LinkMovementMethod()
    text = dataString
}

/**
 * this function use to set style of text view
 * @param resId Int
 */
fun TextView.setStyle(resId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(resId)
    } else setTextAppearance(context, resId)
}

fun TextView.createSpannableString(
    startIndex: Int,
    endIndex: Int,
    isBoldClickableContent: Boolean = false,
    click: () -> Unit = {}
) {
    val spannable = SpannableString(text)
    spannable.setSpan(object : ClickableSpan() {
        override fun onClick(widget: View) {
            click.invoke()
        }
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = context.resources.getColor(R.color.green_00ad31)
            ds.isUnderlineText = false
            ds.isFakeBoldText = isBoldClickableContent
        }
    }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT
    text = spannable
}

