package datn.cnpm.rcsystem.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.basesource.common.loader.LogoLoaderDialog
import com.example.basesource.common.utils.permission.Permission
import com.example.basesource.common.utils.permission.PermissionUtil
import datn.cnpm.rcsystem.common.extension.adjustFontScale
import datn.cnpm.rcsystem.common.extension.hideKeyboard
import com.google.android.material.textfield.TextInputEditText

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), DialogCommonView
//    ConnectionListener {
{
    companion object {
        private const val LOADING_TAG = "LOADING_TAG"
        private const val FIVE_MINUTE_IN_MILLISECOND = (5 * 60 * 1000).toLong()
    }

    private lateinit var permissionUtil: PermissionUtil

    lateinit var binding: VB
        private set
    abstract val bindingInflater: (LayoutInflater) -> VB

    var loader = LogoLoaderDialog()

//    private var dialogTokenUnAuthenticator: DialogCustom? = null
//
//    private var dialogNetworkError: DialogCustom? = null

    private var handler: Handler = Handler(Looper.getMainLooper())

    private lateinit var runnable: Runnable

    private var isAppLoggedIn: Boolean = true

    private var isAppTimeOut: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)

        setContentView(binding.root)
        // prevent app font scale base on font system
        adjustFontScale()
        // Init immediately after create activity
        permissionUtil = PermissionUtil(this)

        setOnTouchOutsideInputFieldEvent()

        runnable = Runnable {
            isAppTimeOut = true
            logoutApp()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchOutsideInputFieldEvent() {
        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val view = currentFocus
                if (view is EditText || view is TextInputEditText) {
                    val outRect = Rect()
                    view.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(
                            event.rawX.toInt(),
                            event.rawY.toInt()
                        )
//                        && v !is BaseCustomInputField
                    ) {
                        view.clearFocus()
                        view.hideKeyboard()
                    }
                }
            }
            true
        }
    }

    /**
     * Request grants permissions.
     *
     * @param permissions (variable number of arguments): List permission want to request
     * (e.g.,[Manifest.permission.ACCESS_COARSE_LOCATION], [Manifest.permission.READ_EXTERNAL_STORAGE],...)
     *
     * @param callback : Return result of request. There are two parameters:
     * areGrantedAll: True if all permissions are granted. False if at least one of those is declined.
     * deniedPermissions: List [Permission] request which are declined by user.
     */
    fun requestPermissions(
        vararg permissions: String,
        callback: (areGrantedAll: Boolean, deniedPermissions: List<Permission>) -> Unit,
    ) {
        permissionUtil.request(*permissions, callback = callback)
    }

    override fun showSingleOptionDialog(
        title: String?,
        message: String,
        button: String,
        listener: DialogButtonClickListener?,
    ): AlertDialog? {
        return buildDialog(title, message)
            .setPositiveButton(button) { dialog, _ ->
                listener?.invoke(dialog) ?: dialog.dismiss()
            }
            .show()
    }

    override fun showSingleOptionDialog(
        title: Int?,
        message: Int,
        button: Int,
        listener: DialogButtonClickListener?,
    ): AlertDialog? {
        return showSingleOptionDialog(
            title = title?.let { getString(it) },
            message = getString(message),
            button = getString(button),
            listener = listener
        )
    }

    override fun showDoubleOptionsDialog(
        title: String?,
        message: String,
        firstButton: String,
        secondButton: String,
        firstButtonListener: DialogButtonClickListener?,
        secondButtonListener: DialogButtonClickListener?,
    ): AlertDialog? {
        return buildDialog(title, message)
            .setPositiveButton(firstButton) { dialog, _ ->
                firstButtonListener?.invoke(dialog) ?: dialog.dismiss()
            }
            .setNegativeButton(secondButton) { dialog, _ ->
                secondButtonListener?.invoke(dialog) ?: dialog.dismiss()
            }
            .show()
    }

    override fun showDoubleOptionsDialog(
        title: Int?,
        message: Int,
        firstButton: Int,
        secondButton: Int,
        firstButtonListener: DialogButtonClickListener?,
        secondButtonListener: DialogButtonClickListener?,
    ): AlertDialog? {
        return showDoubleOptionsDialog(
            title = title?.let { getString(it) },
            message = getString(message),
            firstButton = getString(firstButton),
            secondButton = getString(secondButton),
            firstButtonListener = firstButtonListener,
            secondButtonListener = secondButtonListener
        )
    }

    private fun buildDialog(title: String?, message: String): AlertDialog.Builder {
        return AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(title)
            .setMessage(message)
    }

    internal fun reCreateActivitySmooth() {
        startActivity(intent?.also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    internal fun logoutApp() {
//        val intent = if (isAppTimeOut)
//            Intent(this, LoginActivity::class.java).putExtra(IS_TIME_OUT_KEY, true)
//        else
//            Intent(this, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        startActivity(intent)
    }

    fun showLoading() {
        val loading = supportFragmentManager.findFragmentByTag(LOADING_TAG)
        if (loading != null && loading.isVisible) return

        LogoLoaderDialog().show(supportFragmentManager, LOADING_TAG)

    }

    fun hideLoading() {
        supportFragmentManager.fragments.filter { it.tag == LOADING_TAG }.forEach {
            (it as DialogFragment).dismissAllowingStateLoss()
        }
    }

//    override fun onInternetUnavailable() {
//        // onInternetUnavailable
//        DebugLog.d("onInternetUnavailable")
//        showInternetUnavailable()
//    }

//    private fun showInternetUnavailable() {
//        DebugLog.d("showInternetUnavailable")
//        runOnUiThread {
//            if (dialogNetworkError == null) {
//                dialogNetworkError = DialogCustom(this).apply {
//                    setTitle(R.string.unauthorized_title_message)
//                    setDescription(R.string.networkDisconnect)
//                    setPositiveButton(R.string.text_close) { _, _ ->
//                        dialogNetworkError?.dismiss()
//                        dialogNetworkError = null
//                    }
//                    setCancelable(false)
//                }
//            }
//
//            if (dialogNetworkError?.isShowing == false) {
//                dialogNetworkError?.show()
//            }
//        }
//    }

//    private fun showTokenExpired() {
//        runOnUiThread { showAutoLogoutDialog() }
//    }

//    private fun showAutoLogoutDialog() {
//        if (dialogTokenUnAuthenticator == null) {
//            dialogTokenUnAuthenticator = DialogCustom(this).apply {
//                setTitle(R.string.unauthorized_title_message)
//                setDescription(R.string.unauthorized_description_message)
//                setPositiveButton(R.string.text_close) { _, _ ->
//                    dialogTokenUnAuthenticator?.dismiss()
//                    logoutApp()
//                }
//                setCancelable(false)
//            }
//        }
//
//        if (dialogTokenUnAuthenticator?.isShowing == false && !this.isFinishing) {
//            dialogTokenUnAuthenticator?.show()
//        }
//    }

    override fun onStart() {
        super.onStart()
        if (isAppLoggedIn) {
            startHandler()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopHandler()
    }

    internal fun setStatusAppNotLogin() {
        isAppLoggedIn = false
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        if (isAppLoggedIn) {
            stopHandler()
            startHandler()
        }
    }

    private fun stopHandler() {
        handler.removeCallbacks(runnable)
    }

    private fun startHandler() {
        handler.postDelayed(runnable, FIVE_MINUTE_IN_MILLISECOND)
    }
}
