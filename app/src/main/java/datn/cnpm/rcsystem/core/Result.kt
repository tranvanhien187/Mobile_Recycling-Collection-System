package datn.cnpm.rcsystem.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error<out T>(val errorMessage: ErrorMessage) : Result<T>()

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorCode=${errorMessage}]]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

val Result<*>.failed
    get() = this is Result.Error

fun <T> Flow<Result<T>>.onSucceeded(action: suspend (T) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success<T>) {
            action(result.data)
        }
        return@transform emit(result)
    }

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

val <T> Result<T>.error: Exception?
    get() = (this as? Result.Error)?.errorMessage?.exception

val <T> Result<T>.errorMessage: ErrorMessage?
    get() = (this as? Result.Error)?.errorMessage

val <T> Result<T>.errorCode: String?
    get() = (this as? Result.Error)?.errorMessage?.errorCode

val <T> Result<T>.requireData: T
    get() = (this as Result.Success).data

val <T> Result<T>.requireError: Exception
    get() = (this as Result.Error).errorMessage.exception

fun <T> Flow<Result<T>>.doOnSuccess(
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    action: (T) -> Unit,
): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success<T>) {
            scope.launch {
                action(result.data)
            }
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.onError(
    action: suspend (ErrorMessage) -> Unit = {},
): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Error) {
            result.errorMessage?.let { action(it) }
        }
        return@transform emit(result)
    }

