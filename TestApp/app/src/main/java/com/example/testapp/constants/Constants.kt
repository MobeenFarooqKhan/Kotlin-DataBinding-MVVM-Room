package com.daytranslations.daytrep.constants



private val BASE_URL = "https://jsonplaceholder.typicode.com"

class Constants {
    companion object {
        fun getBaseUrl() : String{
            return "$BASE_URL"
        }
    }
}