package datn.cnpm.rcsystem.common.extension

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager

/**
 * Gey resource ID by name.
 *
 * @param resIdName String? the resource ID name.
 * @param resType String the resource type.
 * @return Int? ID of resource.
 */
fun Context.resIdByName(resIdName: String?, resType: String): Int? {
    return try {
        val resId = resIdName?.let { resources.getIdentifier(it, resType, packageName) }
        if (resId == 0) return null
        return resId
    } catch (ex: Resources.NotFoundException) {
        ex.printStackTrace()
        null
    }
}

/**
 * Check network connectivity.
 *
 * @return true/false connect/disconnect.
 */
fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}
