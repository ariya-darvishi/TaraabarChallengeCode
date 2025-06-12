package net.taraabar.challengecode.data.remote.model.request

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeyValueModel(
    @Json(name = "key") val key: String,
    @Json(name = "value") val value: String
)

@Stable
@Immutable
data class KeyValueModelList(
    val items: List<KeyValueModel>
)