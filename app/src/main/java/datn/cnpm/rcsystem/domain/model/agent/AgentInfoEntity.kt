package datn.cnpm.rcsystem.domain.model.agent

import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.data.entitiy.AddressResponse
import datn.cnpm.rcsystem.data.entitiy.GarbageManagementResponse
import datn.cnpm.rcsystem.data.entitiy.PointResponse
import datn.cnpm.rcsystem.data.entitiy.agent.AgentInfoResponse
import datn.cnpm.rcsystem.data.entitiy.tplace.TPlaceDetailResponse


data class AgentInfoEntity(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val avatar: String,
    val phoneNumber: String,
    val address: AddressResponse? = null,
    val identityCardNumber: String,
    val dateOfBirth: String,
    val tradingPlace: TPlaceDetailResponse? = null,
    val garbageManagement: GarbageManagementResponse? = null,
    val point: PointResponse? = null
)

fun AgentInfoResponse.mapToAgentEntity(): AgentInfoEntity {
    val entity = AgentInfoEntity(
        id,
        name,
        username,
        email,
        avatar,
        phoneNumber,
        address,
        identityCardNumber,
        dateOfBirth,
        tradingPlace,
        garbageManagement,
        point
    )

    SingletonObject.agent = entity

    return entity
}