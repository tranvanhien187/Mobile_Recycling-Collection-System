package datn.cnpm.rcsystem.domain.usecase.history

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetListGarbageHistoryUseCase {
    class Parameters()

    suspend fun getListGarbageHistory(parameters: Parameters = Parameters()): Result<List<BaseItemHistory>>
}

class GetListGarbageHistoryUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetListGarbageHistoryUseCase.Parameters, List<BaseItemHistory>>(ioDispatcher),
    GetListGarbageHistoryUseCase {

    override suspend fun execute(parameters: GetListGarbageHistoryUseCase.Parameters): List<BaseItemHistory> {
        return CRGSRepository.getListGarbageHistory()
    }

    override suspend fun getListGarbageHistory(parameters: GetListGarbageHistoryUseCase.Parameters): Result<List<BaseItemHistory>> {
        return invoke(parameters)
    }
}