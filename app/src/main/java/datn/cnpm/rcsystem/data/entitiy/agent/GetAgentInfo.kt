package datn.cnpm.rcsystem.data.entitiy.agent

import datn.cnpm.rcsystem.data.entitiy.AddressResponse
import datn.cnpm.rcsystem.data.entitiy.TradingPlaceResponse


data class GetAgentInfoResponse(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val phoneNumber: String? = null,
    val address: AddressResponse? = null,
    val tradingPlaces: List<TradingPlaceResponse>? = null
)