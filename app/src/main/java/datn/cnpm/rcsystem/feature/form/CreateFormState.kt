package datn.cnpm.rcsystem.feature.form

import datn.cnpm.rcsystem.domain.model.tplace.TradingPlaceEntity

data class CreateFormState(
    val loading: Boolean = false,
    val tplace: TradingPlaceEntity? = null
)