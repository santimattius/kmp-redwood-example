// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json              = Json { allowStructuredMapKeys = true }
// val characterResponse = json.parse(CharacterResponse.serializer(), jsonString)

package com.santimattius.kmp.redwood.presenter.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("items") val items: List<Item>,
)

@Serializable
data class Item(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("ki")  val ki: String,
    @SerialName("maxKi")  val maxKi: String,
    @SerialName("race")  val race: String,
    @SerialName("description")  val description: String,
    @SerialName("image")  val image: String,
)
