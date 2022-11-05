package datn.cnpm.rcsystem.feature.transportform.mission

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase

data class MissionState(val loading: Boolean = false, val listForm: List<TransportFormFirebase> = emptyList())