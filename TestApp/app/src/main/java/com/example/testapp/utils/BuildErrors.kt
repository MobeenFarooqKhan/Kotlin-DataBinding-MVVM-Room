package com.example.testapp.utils

import org.json.JSONObject
import java.lang.Exception
import java.lang.StringBuilder

class BuildErrors {
    companion object {
        fun buildError(error : String?,responseCode : Int) : String {
            val error = error
            val message = StringBuilder()
            error?.let {
                message.append(JSONObject(it).getString("message"))
                message.append("\n")
            }
          return  message.append("Error Code $responseCode").toString()
        }
    }
}