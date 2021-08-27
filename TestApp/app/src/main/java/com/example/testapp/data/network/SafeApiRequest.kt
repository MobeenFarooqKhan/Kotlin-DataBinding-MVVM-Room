package com.example.testapp.data.network

import com.example.testapp.exceptions.ApiException
import com.example.testapp.utils.BuildErrors
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.lang.StringBuilder

abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response?.isSuccessful) {
            return  response.body()!!
        }else {
            throw  ApiException(BuildErrors.buildError(response.errorBody()?.string(),response.code()))
        }
    }
}