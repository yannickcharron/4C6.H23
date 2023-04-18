package ca.qc.cstj.s09navigationdrawer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckIn(
    val scanId: String,
    val door: Int,
    val scannedDate: String = ""
)
