package ca.qc.cstj.s09navigationdrawer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val x: Double,
    val y: Double,
    val z: Double
)