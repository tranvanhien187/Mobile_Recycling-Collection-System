package datn.cnpm.rcsystem.feature.transportform.detail

import datn.cnpm.rcsystem.domain.model.TFormGarbageDetailEntity
import datn.cnpm.rcsystem.domain.model.TransportForm

data class TransportFormDetailState(val loading: Boolean = false, val form: TransportForm? = null)