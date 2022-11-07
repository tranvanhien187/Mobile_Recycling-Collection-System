package datn.cnpm.rcsystem

import datn.cnpm.rcsystem.domain.model.agent.AgentInfoEntity
import datn.cnpm.rcsystem.domain.model.customer.CustomerEntity
import datn.cnpm.rcsystem.domain.model.staff.StaffInfoEntity


object SingletonObject {
    var customer: CustomerEntity? = null
    var staff: StaffInfoEntity? = null
    var agent: AgentInfoEntity? = null
}