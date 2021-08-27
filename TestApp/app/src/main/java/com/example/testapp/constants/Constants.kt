package com.daytranslations.daytrep.constants



private val BASE_URL = "https://jsonplaceholder.typicode.com"
private val DEBUG_BUILD : Boolean = true
class Constants {
    companion object {
        fun getBaseUrl() : String{
            return "$BASE_URL"
        }
        fun isDebugBuild(): Boolean {
            return DEBUG_BUILD
        }
    }
}