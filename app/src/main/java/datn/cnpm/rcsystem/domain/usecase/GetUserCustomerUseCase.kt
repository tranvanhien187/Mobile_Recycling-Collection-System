package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.CustomerEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetUserCustomerUseCase {
    class Parameters()

    suspend fun getUserInfo(parameters: Parameters = Parameters() ): Result<CustomerEntity>
}

class GetCustomerInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetUserCustomerUseCase.Parameters, CustomerEntity>(ioDispatcher),
    GetUserCustomerUseCase {

    override suspend fun execute(parameters: GetUserCustomerUseCase.Parameters): CustomerEntity {
        return CRGSRepository.getCustomerInfo().mapToEntity()
    }

    override suspend fun getUserInfo(parameters: GetUserCustomerUseCase.Parameters): Result<CustomerEntity> {
        return invoke(parameters)
    }
}