package datn.cnpm.rcsystem.common.extension

import android.app.Dialog
import android.content.Context
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun View.setSafeOnClickListener(delayInterval: Long = 300, onSafeClick: () -> Unit) {
    var timeNow = 0L
    setOnClickListener {
        SystemClock.elapsedRealtime().run {
            if (this - timeNow < delayInterval) {
                return@setOnClickListener
            }
            timeNow = this
            onSafeClick.invoke()
        }
    }
}

fun View.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Dialog.showKeyboardForDialogScreen() {
    this.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}

/**
 * Clear focus on key done.
 *
 * @param keyCode Int the key code.
 * @param keyEvent the [KeyEvent].
 * @return Boolean for on focus change listener.
 */
fun View.clearFocusOnKeyDone(keyCode: Int, keyEvent: KeyEvent): Boolean {
    if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
        clearFocus()
        hideKeyboard()
        return true
    }
    return false
}

/**
 * Clear focus on key done.
 *
 * @param left Int the margin left.
 * @param top Int the margin top.
 * @param right  Int the margin right.
 * @param bottom  Int the margin bottom.
 */
fun View.setMargins(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    lp.setMargins(
        left ?: lp.leftMargin,
        top ?: lp.topMargin,
        right ?: lp.rightMargin,
        bottom ?: lp.bottomMargin
    )
    layoutParams = lp
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

