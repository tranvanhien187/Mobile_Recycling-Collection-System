package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.StaffInfoEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject



interface GetStaffInfoUseCase {
    class Parameters()

    suspend fun getStaffInfo(parameters: Parameters = Parameters()): Result<StaffInfoEntity>
}

class GetStaffInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetStaffInfoUseCase.Parameters, StaffInfoEntity>(ioDispatcher),
    GetStaffInfoUseCase {

    override suspend fun execute(parameters: GetStaffInfoUseCase.Parameters): StaffInfoEntity {
        return CRGSRepository.getStaffInfo().mapToEntity()
    }

    override suspend fun getStaffInfo(parameters: GetStaffInfoUseCase.Parameters): Result<StaffInfoEntity> {
        return invoke(parameters)
    }
}