package datn.cnpm.rcsystem.feature.leaderboard

import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectEntity


data class LeaderboardState(
    val loading: Boolean = false,
    val listResult: List<StatisticStaffCollectEntity>? = null
)
