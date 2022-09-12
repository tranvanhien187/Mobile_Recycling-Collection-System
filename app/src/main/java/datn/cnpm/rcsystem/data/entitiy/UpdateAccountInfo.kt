package datn.cnpm.rcsystem.data.entitiy


data class UpdateAccountInfoRequest(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val street: String,
    val district: String,
    val provinceOrCity: String
)

