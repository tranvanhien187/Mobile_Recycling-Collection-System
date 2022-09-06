package com.example.basesource.common.utils.datetime

import android.os.Build
import datn.cnpm.rcsystem.common.Constant
import datn.cnpm.rcsystem.core.logging.DebugLog
import java.lang.reflect.InvocationTargetException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtil {
    private const val DATE_PATTERN = "dd/MM/yyyy"
    private const val DATE_TIME_SERVER = "yyyy-MM-dd HH:mm:ss.SSSS"
    private const val DATE_SERVER = "yyyy-MM-dd"
    private const val DATE_SERVER_2 = "dd-MM-yyyy"
    private const val DATE_TIME_SERVER_2 = "yyyy-MM-dd'T'HH:mm:ss"
    const val DATE_TIME_SERVER_3 = "yyyy-MM-dd HH:mm:ss.SSS"
    private const val DATE_TIME_SERVER_4 = "dd-MM-yyyy HH:mm:ss"
    private const val DATE_TIME_RESULT = "dd/MM/yyyy | HH:mm:ss"
    const val DATE_AND_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss"
    private const val DATE_TIME_VERTICAL = "dd/MM/yyyy | HH:mm"
    private const val DATE_TIME_VERTICAL_2 = "dd/MM/yyyy HH:mm"
    private const val YEAR_FORMAT = "yyyy"
    private const val TIME_MILLIS = 24 * 60 * 60L * 1000
    const val HORIZONTAL_SIGN = "-"
    const val DAY_ZERO = 0
    const val BACK_DAY = -1
    const val PREVIOUS_7D = -6
    const val PREVIOUS_30D = -29
    const val DAY_90 = 90
    private const val MONTH_YEAR_FORMAT = "MM/yyyy"
    private const val DATE_FORMAT = "dd/MM/yyyy"
    private const val DATE_TRANSFER_FORMAT = "dd/MM/yyyy hh:mm:ss"
    const val DATE_TIME_VERTICAL_3 = "dd/MM/yyyy - HH:mm"
    const val MIN_DATE_SUPPORT = "01/01/1970"
    const val MAX_DATE_SUPPORT = "31/12/2099"

    /**
     * Get current date
     * @return String: current date convert to string
     */
    fun getCurrentDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            formatter.format(date)
        }
    }

    fun getCurrentYear(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(YEAR_FORMAT)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(YEAR_FORMAT, Locale.getDefault())
            formatter.format(date)
        }
    }

    fun getCurrentDateAndTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_PATTERN)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            formatter.format(date)
        }
    }

    fun getCurrentDateAndTimeForResult(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(DATE_TIME_RESULT)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            formatter.format(date)
        }
    }

    fun getCurrentDateAndTimeVertical(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(DATE_TIME_VERTICAL)
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat(DATE_TIME_VERTICAL, Locale.getDefault())
            formatter.format(date)
        }
    }

    /**
     * Get date away from current date
     * @param days: days away from current date
     * @return String: days convert to string
     */
    fun getCalculatedDate(days: Int): String {
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = formatter.parse(getCurrentDate()) ?: Date()
        calendar.add(Calendar.DAY_OF_YEAR, days)
        try {
            return formatter.format(calendar.time)
        } catch (e: ParseException) {
            DebugLog.e("DateTimeUtil getCalculatedDate(), Error in Parsing Date : " + e.message)
        }
        return Constant.EMPTY_STRING
    }

    /**
     * Check input string is date or not
     * @param inDate: input string date
     * @return Boolean: true if input is date and false if input is not date
     */
    private fun isValidDate(inDate: String): Boolean {
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        formatter.isLenient = false
        try {
            formatter.parse(inDate.trim { it <= ' ' })
        } catch (pe: ParseException) {
            return false
        }
        return true
    }

    /**
     * Check date is bigger than current date or not
     * @param date: input date want to check
     * @return Boolean: true if date is bigger than current date and opposite
     */
    fun isBiggerThanCurrentDate(date: String): Boolean {
        if (!isValidDate(date)) {
            return true
        }
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val strDate = formatter.parse(date) ?: Date()
        return strDate.time > System.currentTimeMillis()
    }

    /**
     * Compare date with date
     * @param date1: input string date 1
     * @param date2: input string date 2
     * @param days: distance days
     * @return Boolean: true if date 1 is bigger than date 2 is days right away and opposite
     */
    fun compareDate(date1: String, date2: String, days: Int): Boolean {
        if (!isValidDate(date1) || !isValidDate(date2)) {
            return true
        }
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val strDate1 = formatter.parse(date1) ?: Date()
        val strDate2 = formatter.parse(date2) ?: Date()
        return strDate1.time > strDate2.time + days * TIME_MILLIS
    }

    /**
     * Check input string is date or not
     * @param inDate: input string date
     * @return Boolean: true if input is date and false if input is not date
     */
    private fun isValidDateTime(inDate: String): Boolean {
        val formatter = SimpleDateFormat(DATE_TIME_SERVER_3, Locale.getDefault())
        formatter.isLenient = false
        try {
            formatter.parse(inDate.trim { it <= ' ' })
        } catch (pe: ParseException) {
            return false
        }
        return true
    }

    /**
     * Compare date with date
     * @param date1: input string date 1
     * @param date2: input string date 2
     * @return Long: true if date 1 is bigger than date 2
     */
    fun compareDateTime(date1: String, date2: String): Long {
        if (!isValidDateTime(date1) || !isValidDateTime(date2)) {
            return -1
        }
        return try {
            val formatter = SimpleDateFormat(DATE_TIME_SERVER_3, Locale.getDefault())
            val strDate1 = formatter.parse(date1) ?: Date()
            val strDate2 = formatter.parse(date2) ?: Date()
            strDate1.time - strDate2.time
        } catch (e: IllegalArgumentException) {
            -1
        } catch (e: ParseException) {
            -1
        }
    }

    /**
     * This extension func of Calendar to get date time and convert to string
     * @return String
     */
    fun Calendar.convertToString(): String {
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return formatter.format(time)
    }

    /**
     * This extension func of String to convert string to calendar
     * @return Calendar
     */
    fun String.convertToCalendar(): Calendar {
        val string = this
        if (!isValidDate(string) || string.contains(HORIZONTAL_SIGN)) {
            return Calendar.getInstance()
        }
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val date = formatter.parse(string)
        val cal = Calendar.getInstance()
        cal.time = date ?: Date()

        return cal
    }

    /**
     * This extension func of String to convert string date time get from server
     * to expect date time
     * @return String date time expect dd/mm/yyyy HH:mm:ss
     */
    fun String.convertToExpectDateTime(): String {
        val string = this
        val inputFormat = SimpleDateFormat(DATE_TIME_SERVER, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_AND_TIME_PATTERN, Locale.getDefault())
        return try {
            val date: Date = inputFormat.parse(string) ?: Date()
            outputFormat.format(date)
        } catch (ex: ParseException) {
            DebugLog.d("convertToExpectDateTime()", ex)
            Constant.EMPTY_STRING
        }
    }

    /**
     * This extension func of String to convert string date time get from server like yyyy-MM-dd'T'HH:mm:ss
     * to expect date time
     * @return String date time expect dd/mm/yyyy
     */
    fun String.formatDateTime(): String {
        try {
            val string = this
            val inputFormat = SimpleDateFormat(DATE_TIME_SERVER_2, Locale.getDefault())
            val outputFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            val date: Date = inputFormat.parse(string) ?: Date()
            return outputFormat.format(date)
        } catch (e: Exception) {
            DebugLog.e(e.message.toString())
        }
        return this
    }

    /**
     * This extension func of String to convert string date get from server yyyy-MM-dd
     * to expect date time
     * @return String date time expect dd/mm/yyyy
     */
    fun String.convertToExpectDateWithoutTime(): String {
        val inputFormat = SimpleDateFormat(DATE_SERVER, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

        val date: Date = inputFormat.parse(this) ?: Date()
        return outputFormat.format(date)
    }

    /**
     * This extension func of String to convert string date get from server dd-MM-yyyy
     * to expect date time
     * @return String date time expect dd/mm/yyyy
     */
    fun String.convertToExpectDateWithoutTime2(): String {
        val inputFormat = SimpleDateFormat(DATE_SERVER_2, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

        val date: Date = inputFormat.parse(this) ?: Date()
        return outputFormat.format(date)
    }

    /**
     * This extension func of String to convert string date get from server dd-MM-yyyy HH:mm:ss
     * to expect date time
     * @return String date time expect dd/mm/yyyy
     */
    fun String.convertToExpectDateWithoutTime3(): String {
        val inputFormat = SimpleDateFormat(DATE_TIME_SERVER_4, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_TIME_VERTICAL_2, Locale.getDefault())

        val date: Date = inputFormat.parse(this) ?: Date()
        return outputFormat.format(date)
    }

    /**
     * This extension func of String to convert string date time get from server
     * to expect date time
     * @return String date time expect dd/mm/yyyy
     */
    fun String.convertToExpectDate(): String {
        val string = this
        val inputFormat = SimpleDateFormat(DATE_TIME_SERVER, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return try {
            val date: Date = inputFormat.parse(string) ?: Date()
            outputFormat.format(date)
        } catch (ex: ParseException) {
            DebugLog.d("convertToExpectDateTime()", ex)
            Constant.EMPTY_STRING
        }
    }

    /**
     * This extension func of String to convert string date time get from server
     * to expect date time
     * @return String date time expect dd/mm/yyyy | HH:mm
     */
    fun String.convertToExpectDateVertical(): String {
        val string = this
        val inputFormat = SimpleDateFormat(DATE_TIME_SERVER, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_TIME_VERTICAL, Locale.getDefault())
        return try {
            val date: Date = inputFormat.parse(string) ?: Date()
            outputFormat.format(date)
        } catch (ex: ParseException) {
            DebugLog.d("convertToExpectDateTime()", ex)
            Constant.EMPTY_STRING
        }
    }

    /**
     * This extension func of String to convert string date time get from server
     * to expect date time
     * @return String date time expect dd/mm/yyyy HH:mm
     */
    fun String.convertToExpectDateHourMinute(): String {
        val string = this
        val inputFormat = SimpleDateFormat(DATE_TIME_SERVER, Locale.getDefault())
        val outputFormat = SimpleDateFormat(DATE_TIME_VERTICAL_2, Locale.getDefault())
        return try {
            val date: Date = inputFormat.parse(string) ?: Date()
            outputFormat.format(date)
        } catch (ex: ParseException) {
            DebugLog.d("convertToExpectDateTime()", ex)
            Constant.EMPTY_STRING
        }
    }

    /**
     * This extension func of String to convert string date time get from server
     * to expect date time
     * @return String date time expect dd/mm/yyyy | HH:mm
     */
    fun String.convertToExpectDateVerticalVer2(
        inputFormatString: String = DATE_TIME_SERVER,
        outputFormatString: String = DATE_TIME_VERTICAL
    ): String {
        val string = this
        val inputFormat = SimpleDateFormat(inputFormatString, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputFormatString, Locale.getDefault())

        return try {
            val date: Date = inputFormat.parse(string) ?: Date()
            outputFormat.format(date)
        } catch (e: ParseException) {
//            string.millisecondsToDisplayDateTime()
            DebugLog.e(e.message.toString())
            Constant.EMPTY_STRING
        } catch (e: InvocationTargetException) {
//            string.millisecondsToDisplayDateTime()
            DebugLog.e(e.message.toString())
            Constant.EMPTY_STRING
        }
    }

    fun getMonthYearFromTimeInMillis(timeInMillis: Long): String {
        val formatter = SimpleDateFormat(MONTH_YEAR_FORMAT)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return formatter.format(calendar.time)
    }

    fun getDateFromTimeInMillis(timeInMillis: Long): String {
        val formatter = SimpleDateFormat(DATE_FORMAT)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return formatter.format(calendar.time)
    }

    fun getTransferDateFromTimeInMillis(timeInMillis: Long): String {
        val formatter = SimpleDateFormat(DATE_TRANSFER_FORMAT)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return formatter.format(calendar.time)
    }

    fun getYearFromTimeInMillis(timeInMillis: Long): String {
        val formatter = SimpleDateFormat(YEAR_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return formatter.format(calendar.time)
    }

    fun getNumberWithTwoCharacter(value: Int): String {
        return if (value < 10) {
            "0$value"
        } else {
            "$value"
        }
    }

//    fun getDateFromInput(textInput: String): Long {
//        if (textInput.length != Constant.DATE_PATTERN_DDMMYYYY.length) {
//            return Constant.INVALID_DATE_TIME_VALUE
//        } else {
//            try {
//                val dayOfMonth = textInput.substring(0, 2).toInt()
//                val month = textInput.substring(3, 5).toInt()
//                val year = textInput.substring(6, 10).toInt()
//                Log.d("getDateFromInput", "$dayOfMonth $month $year")
//                val calendar = Calendar.getInstance()
//                if (dayOfMonth > 30 || month > 12) {
//                    return Constant.INVALID_DATE_TIME_VALUE
//                }
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                calendar.set(Calendar.MONTH, month - 1)
//                calendar.set(Calendar.YEAR, year)
//                return calendar.timeInMillis
//            } catch (ex: NumberFormatException) {
//                return Constant.INVALID_DATE_TIME_VALUE
//            }
//        }
//    }

    fun getPre90Days(): String {
        val pre90Date = System.currentTimeMillis() - DAY_90 * TIME_MILLIS
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return formatter.format(pre90Date)
    }

    fun getPre7Days(): String {
        val pre7Date = System.currentTimeMillis() + PREVIOUS_7D * TIME_MILLIS
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return formatter.format(pre7Date)
    }

    //ID card/passport is valid for 15 years
    private const val DAYS_OF_15_YEARS = 5475
    fun isIdCardOutOfDate(idCardIssueDate: String): Boolean {
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val currentDate = formatter.parse(getCurrentDate()) ?: Date()
        val issueDate = formatter.parse(idCardIssueDate) ?: Date()
        return issueDate.time + DAYS_OF_15_YEARS * TIME_MILLIS < currentDate.time
    }

    fun getDayBetweenTwoDate(startDate: String, endDate: String): Long {
        val diff = (SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(endDate)?.time
            ?: 0) - (SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(startDate)?.time
            ?: 0)
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        return hours / 24
    }

    fun displayPercent(startDate: String, endDate: String): Double {
        val dayBetweenTwoDate = getDayBetweenTwoDate(
            startDate = getCurrentDate(),
            endDate = endDate
        )
        val days = getDayBetweenTwoDate(
            startDate = startDate,
            endDate = endDate
        )
        val onePercent: Double = (100.0 / days)
        return 100 - (onePercent * dayBetweenTwoDate)
    }

    fun formatDateTime(dateTimeValue: String?, formatDateTime: String): String {
        if (dateTimeValue.isNullOrEmpty() || dateTimeValue.toLongOrNull() == null)
            return dateTimeValue ?: Constant.EMPTY_STRING
        val date = Date(dateTimeValue.toLong())
        val format = SimpleDateFormat(formatDateTime)
        return format.format(date)
    }

    /**
     * this function use to format date formatted as firstFormat to date formatted as secondFormat
     * @receiver String
     * @param firstFormat String
     * @param secondFormat String
     * @return String
     */
    fun String.convertDateTime(
        firstFormat: String = DATE_TIME_SERVER_4,
        secondFormat: String = DATE_TIME_VERTICAL_2
    ): String {
        val inputFormat = SimpleDateFormat(firstFormat, Locale.getDefault())
        val outputFormat = SimpleDateFormat(secondFormat, Locale.getDefault())

        val date: Date = inputFormat.parse(this) ?: Date()
        return outputFormat.format(date)
    }

}
