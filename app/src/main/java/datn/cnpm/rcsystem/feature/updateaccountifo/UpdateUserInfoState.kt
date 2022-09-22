package datn.cnpm.rcsystem.feature.updateaccountifo

import datn.cnpm.rcsystem.domain.model.UserEntity

data class UpdateUserInfoState(val loading: Boolean = false, val user: UserEntity? = null)