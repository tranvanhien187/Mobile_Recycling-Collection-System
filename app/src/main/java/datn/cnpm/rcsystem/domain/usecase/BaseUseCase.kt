package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.ErrorMessage
import datn.cnpm.rcsystem.core.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(ErrorMessage(exception = e))
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}