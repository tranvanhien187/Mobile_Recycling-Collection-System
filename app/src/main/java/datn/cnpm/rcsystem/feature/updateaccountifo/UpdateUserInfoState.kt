package datn.cnpm.rcsystem.feature.updateaccountifo

import datn.cnpm.rcsystem.domain.model.CustomerEntity

data class UpdateUserInfoState(val loading: Boolean = false, val user: CustomerEntity? = null)