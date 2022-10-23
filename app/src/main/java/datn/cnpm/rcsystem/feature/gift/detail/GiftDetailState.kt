package datn.cnpm.rcsystem.feature.gift.detail

import datn.cnpm.rcsystem.domain.model.gift.GiftDetailEntity

data class GiftDetailState(
    val loading: Boolean = false,
    val gift: GiftDetailEntity? = null
)