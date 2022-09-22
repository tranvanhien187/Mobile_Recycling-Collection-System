package datn.cnpm.rcsystem.feature.updateaccountifo

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface UpdateUserInfoEvent {
    data class ValidateField(
        val errorName: ErrorCode? = null,
        val errorIdentityCard: ErrorCode? = null,
        val errorStreet: ErrorCode? = null,
        val errorDistrict: ErrorCode? = null,
        val errorProvinceCity: ErrorCode? = null
    ) : UpdateUserInfoEvent
    object UpdateSuccess : UpdateUserInfoEvent
    data class RegisterFailure(
        val error: String
    )  : UpdateUserInfoEvent
}


