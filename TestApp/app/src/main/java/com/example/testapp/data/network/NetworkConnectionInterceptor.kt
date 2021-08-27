package com.example.testapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.testapp.exceptions.NoInternetException
import com.example.testapp.utils.LogData
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.net.SocketTimeoutException


class NetworkConnectionInterceptor(
    context: Context
) : Interceptor, HttpLoggingInterceptor.Logger {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")
        //SSL handshake timed out
        if (isConnectionTimedOut(chain))
            throw SocketTimeoutException()

      //  LogData("app","Request L:"+chain.proceed(chain.request()))
        return chain.proceed(chain.request())
    }

     private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }

    private fun isConnectionTimedOut(chain: Interceptor.Chain): Boolean {
        try {
            val response = chain.proceed(chain.request())
            val content = response.toString()
            response.close()
            LogData( "isConnectionTimedOut() => $content")
        } catch (e: SocketTimeoutException) {
            return true
        }
        return false
    }

    override fun log(message: String?) {
        LogData("Log:$message")
    }
//    fun getLoggin(): HttpLoggingInterceptor? {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return logging
//    }

}