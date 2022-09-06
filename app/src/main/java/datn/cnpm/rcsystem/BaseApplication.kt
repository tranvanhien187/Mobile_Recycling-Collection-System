package datn.cnpm.rcsystem

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

//    override fun onTerminate() {
//        super.onTerminate()
//        removeInternetConnectionListener()
//    }
//
//    companion object {
//        private val sRequestConnectionListener: MutableList<ConnectionListener> = ArrayList()


//        fun setInternetConnectionListener(mInternetConnectionListener: ConnectionListener?) {
//            mInternetConnectionListener?.let { sRequestConnectionListener.add(Constant.INT_ZERO, it) }
//        }
//
//        private fun removeInternetConnectionListener() {
//            sRequestConnectionListener.clear()
//        }
//
//        fun getInternetConnectionListener(): ConnectionListener? {
//            return sRequestConnectionListener[Constant.INT_ZERO]
//        }
//    }
}
