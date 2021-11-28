package com.sdimosikvip.weather.sources

import com.sdimosikvip.weather.api.ResultResponse
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultResponse.success(body)
            }
            return errorNetwork(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return errorNetwork(e.message ?: e.toString())
        }
    }

    private fun <T> errorNetwork(message: String): ResultResponse<T> {
        Timber.e(message)
        return ResultResponse.error(
            "Network call has failed for a following reason: $message"
        )
    }

}
