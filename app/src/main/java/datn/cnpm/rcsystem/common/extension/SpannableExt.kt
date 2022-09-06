package datn.cnpm.rcsystem.common.extension

import android.text.Layout
import android.text.Spannable
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan

/**
 * This Spannable Extension.
 * @param color Int the text color to change.
 * @param subString String the content of the text to change color.
 */
fun Spannable.setColor(subString: String, color: Int) {
    val dataString = this.toString()
    val startIndex = dataString.indexOf(subString)
    val endIndex = startIndex + subString.length

    if (startIndex != -1) {
        this.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * This Spannable Extension.
 * @param color Int the text color to change.
 * @param subString String the content of the text to change color.
 * @param alignment Layout.Alignment the style Alignment.
 */
fun Spannable.setAlignment(subString: String, alignment: Layout.Alignment) {
    val dataString = this.toString()
    val startIndex = dataString.indexOf(subString)
    val endIndex = startIndex + subString.length

    if (startIndex != -1) {
        this.setSpan(
            AlignmentSpan.Standard(alignment),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}
