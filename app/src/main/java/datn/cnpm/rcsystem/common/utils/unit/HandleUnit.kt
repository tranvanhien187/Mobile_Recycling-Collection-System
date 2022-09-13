package datn.cnpm.rcsystem.common.utils.unit

import android.content.res.Resources
import android.util.DisplayMetrics

object HandleUnit {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    fun dpToPx(dp: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return  Math.round(dp * scale + 0.5f)
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    fun pxToDp(px: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return px / (scale / DisplayMetrics.DENSITY_DEFAULT)
    }
}
