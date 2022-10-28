package datn.cnpm.rcsystem.feature.form.gift.create

import datn.cnpm.rcsystem.domain.model.gift.GiftDetailEntity

data class CreateFormGiftState(
    val loading: Boolean = false,
    val gift: GiftDetailEntity? = null
)