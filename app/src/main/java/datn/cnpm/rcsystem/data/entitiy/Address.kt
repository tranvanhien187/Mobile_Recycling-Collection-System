package datn.cnpm.rcsystem.data.entitiy

data class AddressResponse(
    private var street: String? = null,
    private val district: String? = null,
    private val provinceOrCity: String? = null,
)