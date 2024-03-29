package datn.cnpm.rcsystem.data.entitiy


data class GetDealerInfoResponse(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val phoneNumber: String? = null,
    val address: AddressResponse? = null,
    val tradingPlaces: List<TradingPlaceResponse>? = null
)