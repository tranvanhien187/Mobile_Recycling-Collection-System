package datn.cnpm.rcsystem.feature.gift.list

import datn.cnpm.rcsystem.domain.model.gift.GiftEntity

data class GiftState(
    val loading: Boolean = false,
    val listGift: List<GiftEntity> = emptyList()
)