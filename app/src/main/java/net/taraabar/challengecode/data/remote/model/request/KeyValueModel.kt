package net.taraabar.challengecode.data.remote.model.request

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import net.taraabar.challengecode.utils.CargoDetailItem

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

object KeyValueModelListMockData {
    val MOCK_DATA = KeyValueModelList(
        listOf(
            KeyValueModel(key = CargoDetailItem.ORIGIN_CITY.fieldName, value = "تهران"),
            KeyValueModel(key = CargoDetailItem.DESTINATION_CITY.fieldName, value = "شیراز"),
            KeyValueModel(key = CargoDetailItem.CARGO_TYPE.fieldName, value = "کفش"),
        )
    )
}
