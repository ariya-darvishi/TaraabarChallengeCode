package net.taraabar.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Common500Errors(
    @Json(name = "code")
    val code: Any?,
    @Json(name = "errorMessage")
    val errorMessage: String?,
    @Json(name = "messageCode")
    val messageCode: Int?,
    @Json(name = "type")
    val type: String?
)