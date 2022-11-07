package datn.cnpm.rcsystem.domain.usecase.agent

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.agent.AgentInfoEntity
import datn.cnpm.rcsystem.domain.model.agent.mapToAgentEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetAgentInfoUseCase {
    class Parameters

    suspend fun getAgentInfo(parameters: Parameters = Parameters()): Result<AgentInfoEntity>
}

class GetAgentInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetAgentInfoUseCase.Parameters, AgentInfoEntity>(ioDispatcher),
    GetAgentInfoUseCase {

    override suspend fun execute(parameters: GetAgentInfoUseCase.Parameters): AgentInfoEntity {
        return CRGSRepository.getAgentInfo().mapToAgentEntity()
    }

    override suspend fun getAgentInfo(parameters: GetAgentInfoUseCase.Parameters): Result<AgentInfoEntity> {
        return invoke(parameters)
    }
}