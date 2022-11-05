package datn.cnpm.rcsystem.domain.usecase.history

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetListGiftHistoryUseCase {
    class Parameters

    suspend fun getListGiftHistory(parameters: Parameters = Parameters()): Result<List<BaseItemHistory>>
}

class GetListGiftHistoryUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetListGiftHistoryUseCase.Parameters, List<BaseItemHistory>>(ioDispatcher),
    GetListGiftHistoryUseCase {

    override suspend fun execute(parameters: GetListGiftHistoryUseCase.Parameters): List<BaseItemHistory> {
        return CRGSRepository.getListGiftHistory()
    }

    override suspend fun getListGiftHistory(parameters: GetListGiftHistoryUseCase.Parameters): Result<List<BaseItemHistory>> {
        return invoke(parameters)
    }
}