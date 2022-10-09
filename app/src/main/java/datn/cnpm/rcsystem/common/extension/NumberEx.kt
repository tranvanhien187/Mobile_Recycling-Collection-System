package datn.cnpm.rcsystem.common.extension

import android.content.Context

fun Context.dpToPx(value: Int) : Int = (value * resources.displayMetrics.density).toInt()