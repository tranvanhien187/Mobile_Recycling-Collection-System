package datn.cnpm.rcsystem.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.common.Constant
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object CommonUtils {
    /**
     * This method using to open web browser
     *
     * @param context [Context]
     * @param url url
     */
    fun openWebPage(context: Context, url: String?) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }


    /**
     * This method using to call to a phone number
     *
     * @param context [Context]
     * @param phoneNumber phone number
     */
    fun openPhoneCall(context: Context, phoneNumber: String?) {
        val number: Uri = Uri.parse(phoneNumber)
        val intent = Intent(Intent.ACTION_CALL, number)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * This method using to show a toast message
     *
     * @param context [Context]
     * @param message message
     */
    fun openToastDialog(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    /**
     * This method using to convert Base64 string to bitmap
     *
     * @param base64String base64 string
     */
    fun convertBase64StringToBitmap(base64String: String): Bitmap {
        var base64Image = base64String
        if (base64String.split(",").toTypedArray().size > 1) {
            base64Image = base64String.split(",").toTypedArray()[1]
        }
        val bitmapData = Base64.decode(base64Image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
    }

    fun getScreenWidthHeight(activity: Activity): Pair<Int, Int> {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        return Pair(width, height)
    }

    /**
     * This method using to get time now and format time
     *
     * @param format format you want
     */
    fun getTimeNow(format: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(format)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            formatter.format(date)
        }
    }

    /**
     * This method using to format account number or phone number, separat by " "
     *
     * @param accountOrPhoneNumber
     * @param firstIndex first position to add devider " "
     * @param secondIndex second position to add devider " "
     * @param thirdIndex third position to add devider " "
     */
    fun formatAccountOrPhoneNumber(
        accountOrPhoneNumber: String,
        firstIndex: Int,
        secondIndex: Int,
        thirdIndex: Int
    ): String {
        val devider = " "
        val formatted = StringBuilder()
        var index = 0
        for (character in accountOrPhoneNumber) {
            formatted.append(character)
            index++
            if (index == firstIndex || index == secondIndex || index == thirdIndex) {
                formatted.append(devider)
                index++
            }
        }
        return formatted.toString()
    }

    fun appendString(vararg args: String): String {
        val appStringBuilder = StringBuilder()
        for (append in args) {
            appStringBuilder.append(append)
        }
        return appStringBuilder.toString()
    }

    fun highlightText(
        textView: TextView,
        startIndex: Int,
        endIndex: Int,
        context: Context,
        onClickFunction: () -> Unit
    ) {
        val spannableString = SpannableString(textView.text)

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                onClickFunction.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.purple_200)),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    fun hideSoftKeyBoard(context: Context?, view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Double.toPrices(): String {
//        val result = this.replace(".0","")
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("VND")
        return format.format(this)
    }

    fun String.validateEmail(): String {
        val matcherEmailFormat: Matcher =
            Pattern.compile(Constant.ALPHANUMERIC_EMAIL_FORMAT).matcher(this)
        return if (matcherEmailFormat.matches()) Constant.EMAIL_NOT_VALID
        else Constant.EMAIL_VALID
    }

    fun Int?.toPoint(): String {
        return NumberFormat.getNumberInstance(Locale.US).format(this ?: 0) + " points"
    }
}
