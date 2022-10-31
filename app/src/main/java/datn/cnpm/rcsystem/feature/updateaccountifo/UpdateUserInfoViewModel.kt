package datn.cnpm.rcsystem.feature.updateaccountifo

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.usecase.GetCustomerInfoUseCase
import datn.cnpm.rcsystem.domain.usecase.UpdateUserInfoUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdateUserInfoViewModel @Inject constructor(
    private val updateUserInfoUseCase: UpdateUserInfoUseCase) :
    BaseViewModel<UpdateUserInfoState, UpdateUserInfoEvent>() {
    override fun initState() = UpdateUserInfoState()

    var uuid: String = ""
    var avatarFile: File? = null

    fun updateUserInfo(
        avatar: File,
        name: String,
        phoneNumber: String,
        identityCard: String,
        dateOfBirth: String,
        street: String,
        district: String,
        provinceCity: String
    ) {
        viewModelScope.launch {
            val errorName = if (name.isNotEmpty()) null else ErrorCode.EMPTY_NAME
            val errorICN = if (identityCard.isNotEmpty()) null else ErrorCode.EMPTY_IDC
            val errorStreet = if (street.isNotEmpty()) null else ErrorCode.EMPTY_STREET
            val errorDistrict = if (district.isNotEmpty()) null else ErrorCode.EMPTY_DISTRICT
            val errorProvinceCity =
                if (provinceCity.isNotEmpty()) null else ErrorCode.EMPTY_PROVINCE_CITY

            if (errorName == null && errorICN == null && errorStreet == null && errorDistrict == null && errorProvinceCity == null) {
                dispatchState(currentState.copy(loading = true))
                val response = updateUserInfoUseCase.updateAccountInfo(
                    UpdateUserInfoUseCase.Parameters(
                        avatar,
                        uuid,
                        name,
                        phoneNumber,
                        identityCard,
                        dateOfBirth,
                        street, district, provinceCity
                    )
                )
                dispatchEvent(UpdateUserInfoEvent.ValidateField())
                if (response.failed) {
                    dispatchState(currentState.copy(loading = false))
                    when (response.requireError.errorCode) {
                        ErrorCode.CAN_NOT_UPDATE_WITH_ID_USER -> {
                        }
                        else -> {
                            dispatchEvent(UpdateUserInfoEvent.RegisterFailure(response.requireError.exception.message.toString()))
                        }
                    }

                } else {
                    dispatchState(currentState.copy(loading = false))
                    dispatchEvent(UpdateUserInfoEvent.UpdateSuccess)
                }
            } else {
                dispatchEvent(
                    UpdateUserInfoEvent.ValidateField(
                        errorName,
                        errorICN,
                        errorStreet,
                        errorDistrict,
                        errorProvinceCity
                    )
                )
            }
        }
    }
}