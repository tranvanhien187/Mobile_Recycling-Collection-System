package datn.cnpm.rcsystem.core.logging

import android.util.Log

object DebugLog {
  private fun createLog(log: String, methodName: String, lineNumber: Int): String {
    val buffer = StringBuffer()
    buffer.append("[")
    buffer.append(methodName)
    buffer.append(":")
    buffer.append(lineNumber)
    buffer.append("]")
    buffer.append(log)

    return buffer.toString()
  }

  private fun getLogAddress(stackTraces: Array<StackTraceElement>): Triple<String, String, Int> {
    return stackTraces.getOrNull(1)?.let { element ->
      Triple(element.fileName, element.methodName, element.lineNumber)
    } ?: Triple("Not found", "Not found", -1)
  }

  fun e(message: String) {
    println(message)
    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.e(className, createLog(message, methodName, lineNumber))
  }

  fun e(error: Throwable) {
    println(error)
    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.e(className, createLog(error.toString(), methodName, lineNumber))
  }

  fun i(message: String) {
    println(message)

    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.i(className, createLog(message, methodName, lineNumber))
  }

  fun v(message: String) {
    println(message)

    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.v(className, createLog(message, methodName, lineNumber))
  }

  fun w(message: String) {
    println(message)

    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.w(className, createLog(message, methodName, lineNumber))
  }

  fun d(obj: Any?) {
    println(obj.toString())

    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    Log.d(className, createLog(obj.toString(), methodName, lineNumber))
  }

  fun d(tag: String, obj: Any?) {
    println(obj.toString())

    // Throwable instance must be created before any methods
    val (className, methodName, lineNumber) = getLogAddress(Throwable().stackTrace)
    getLogAddress(Throwable().stackTrace)
    Log.d(tag, "[$className]${createLog(obj.toString(), methodName, lineNumber)}")
  }
}