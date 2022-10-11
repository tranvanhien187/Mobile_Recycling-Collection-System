package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.UpdateUserInfoRequest
import datn.cnpm.rcsystem.data.entitiy.UpdateUserInfoResponse
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject


interface UpdateUserInfoUseCase {
    data class Parameters(
        val avatar: File,
        val uuid: String,
        val name: String,
        val phoneNumber: String,
        val ICN: String,
        val dOB: String,
        val street: String,
        val district: String,
        val provinceOrCity: String,
    )

    suspend fun updateAccountInfo(parameters: Parameters): Result<UpdateUserInfoResponse>
}

class UpdateUserInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<UpdateUserInfoUseCase.Parameters, UpdateUserInfoResponse>(ioDispatcher),
    UpdateUserInfoUseCase {

    override suspend fun execute(parameters: UpdateUserInfoUseCase.Parameters): UpdateUserInfoResponse {
        return CRGSRepository.updateUserInfo(
            UpdateUserInfoRequest(
                parameters.avatar,
                parameters.uuid,
                parameters.name,
                parameters.phoneNumber,
                parameters.ICN,
                parameters.dOB,
                parameters.street,
                parameters.district,
                parameters.provinceOrCity,
            )
        )
    }

    override suspend fun updateAccountInfo(parameters: UpdateUserInfoUseCase.Parameters): Result<UpdateUserInfoResponse> {
        return invoke(parameters)
    }
}