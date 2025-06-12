package net.taraabar.network.base

import com.squareup.moshi.Moshi
import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ErrorResponse<T> {
            return error.message?.let {
                ErrorResponse(0, it)
            } ?: ErrorResponse(0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body == null || response.code() == 204) {
                        EmptyResponse()
                    } else {
                        SuccessResponse(body)
                    }
                }

                else -> {
                    if (response.code() == 401) {
                        EmptyResponse()
                    } else {
                        response.errorBody()?.let {
                            val errorBodyString = it.string()
                            try {
                                TODO()
//                                val serializedError = Common500ErrorsJsonAdapter(
//                                    Moshi.Builder().build()
//                                ).fromJson(errorBodyString)
//                                serializedError?.errorMessage?.let { mes ->
//                                    ErrorResponse(response.code(), mes)
//                                } ?: ErrorResponse(response.code())

                            } catch (e: Exception) {
                                ErrorResponse(response.code(), errorBodyString)
                            }


                        } ?: ErrorResponse(response.code())
                    }
                }
            }
        }
    }
}

class EmptyResponse<T> : ApiResponse<T>()
data class ErrorResponse<T>(
    val errorCode: Int,
    val message: String = "در حال حاظر مشکلی در ارتباط با سرور پیش آمده است، لطفا بعدا تلاش فرمائید."
) : ApiResponse<T>()

data class SuccessResponse<T>(val body: T) : ApiResponse<T>()