package datn.cnpm.rcsystem.feature.gift.list

import datn.cnpm.rcsystem.domain.model.GiftEntity

data class GiftState(
    val loading: Boolean = false,
    val listPlace: List<GiftEntity> = emptyList()
)