package datn.cnpm.rcsystem

import datn.cnpm.rcsystem.domain.model.CustomerEntity
import datn.cnpm.rcsystem.domain.model.StaffInfoEntity


object SingletonObject {
    var customer: CustomerEntity? = null
    var staff: StaffInfoEntity? = null
}