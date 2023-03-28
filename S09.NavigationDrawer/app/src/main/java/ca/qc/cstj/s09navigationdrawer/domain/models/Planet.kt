package ca.qc.cstj.s09navigationdrawer.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val href: String,
    val name: String,
    val discoveredBy: String,
    @SerialName("icon") val image: String,
    val discoveryDate: String,
    val temperature: Int,
    val lightspeed: String,
    val satellites: List<String>,
    val position: Position
)