package datn.cnpm.rcsystem.feature.form.garbage.create

import datn.cnpm.rcsystem.domain.model.tplace.TradingPlaceEntity

data class CreateGarbageFormState(
    val loading: Boolean = false,
    val tplace: TradingPlaceEntity? = null
)