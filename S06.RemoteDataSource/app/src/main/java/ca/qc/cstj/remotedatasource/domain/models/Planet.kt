package ca.qc.cstj.remotedatasource.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Planet(val name: String,
                  @SerialName("icon") var image: String,
                  var temperature: Double)
