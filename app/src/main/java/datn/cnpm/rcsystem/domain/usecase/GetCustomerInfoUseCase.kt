package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.CustomerEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetCustomerInfoUseCase {
    class Parameters()

    suspend fun getCustomerInfo(parameters: Parameters = Parameters() ): Result<CustomerEntity>
}

class GetCustomerInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetCustomerInfoUseCase.Parameters, CustomerEntity>(ioDispatcher),
    GetCustomerInfoUseCase {

    override suspend fun execute(parameters: GetCustomerInfoUseCase.Parameters): CustomerEntity {
        return CRGSRepository.getCustomerInfo().mapToEntity()
    }

    override suspend fun getCustomerInfo(parameters: GetCustomerInfoUseCase.Parameters): Result<CustomerEntity> {
        return invoke(parameters)
    }
}