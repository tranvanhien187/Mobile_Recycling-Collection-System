package datn.cnpm.rcsystem.feature.dashboard.agent

import datn.cnpm.rcsystem.domain.model.agent.AgentInfoEntity


data class DashboardAgentState(val loading: Boolean = false, val agentEntity: AgentInfoEntity? = null )