package com.example.testapp.data.network


import com.daytranslations.daytrep.constants.Constants
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.db.entities.user.User

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface MyApi {

    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>
    @GET("users")
    suspend fun getUsers() : Response<List<User>>
    @GET("comments")
    suspend fun getcomments() : Response<List<User>>
//
//    @FormUrlEncoded
//    @POST("getProfile")
//    suspend fun getProfile(
//        @Field("user_id") user_id: String,
//        @Field("device_token") device_token: String
//    ) : Response<AuthResponse>
//
//    // suspend can be paused and resumed at later time. suspend is the center of enverything in coroutienr
//    @FormUrlEncoded
//    @POST("changePassword")
//    suspend fun changePassword(
//            @Field("user_id") user_id: String,
//            @Field("old_password") old_password: String,
//            @Field("new_password") new_password: String,
//            @Field("confirm_password") confirm_password: String,
//            @Field("device_token") device_token: String
//    ) : Response<AuthResponse>
//
//    @FormUrlEncoded
//    @POST("login")
//    suspend fun forgotPassword(
//        @Field("email") email: String
//    ) : Response<AuthResponse>
//
//    @FormUrlEncoded
//    @POST("callHistory")
//    suspend fun getCallHistoryAll(
//        @FieldMap data: HashMap<String, String>,
//        @Field("per_page") per_page: Int,
//        @Field("page_no") page_no: Int,
//        @Field("user_id") user_id: String,
//    ) : Response<History>
//
//    @POST("updateFieldInterpretation")
//    suspend fun updateFieldOfInterpretation(
//        @Body  file : RequestBody
//    ) : Response<AuthResponse>
//
//    @FormUrlEncoded
//    @POST("updateProfile")
//    suspend fun updateProfile(
//        @Part file: MultipartBody.Part?,
//        @Part("user_id") userId: RequestBody,
//        @Part("first_name") first_name: RequestBody,
//        @Part("last_name") last_name: RequestBody,
//        @Part("skypeid") skypeid: RequestBody,
//        @Part("phone") phone: RequestBody,
//        @Part("native_lang") native_lang: RequestBody?,
//        @Part("payment") payment: RequestBody?,
//        @Part("payment_id_paypal") payment_id_paypal: RequestBody?,
//        @Part("payment_id_payoneer") payment_id_payoneer: RequestBody?,
//        @Part("device_token") device_token:  RequestBody?
//    ): Response<AuthResponse>
//    @Multipart
//    @POST("updateProfile")
//    suspend fun updateProfileWithoutPicture(
//        @Part("user_id") userId: RequestBody,
//        @Part("first_name") first_name: RequestBody,
//        @Part("last_name") last_name: RequestBody,
//        @Part("skypeid") skypeid: RequestBody,
//        @Part("phone") phone: RequestBody,
//        @Part("native_lang") native_lang: RequestBody?,
//        @Part("payment") payment: RequestBody?,
//        @Part("payment_id_paypal") payment_id_paypal: RequestBody?,
//        @Part("payment_id_payoneer") payment_id_payoneer: RequestBody?,
//        @Part("device_token") device_token: RequestBody?
//    ): Response<AuthResponse>
//
//
//    @FormUrlEncoded
//    @POST("userSignUp")
//    suspend fun userSignUp(@Field("username") username: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ) : Response<AuthResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor : NetworkConnectionInterceptor
        ) : MyApi {

            val logging = HttpLoggingInterceptor(networkConnectionInterceptor)
            logging.level = HttpLoggingInterceptor.Level.BODY;
            val okkHttpclient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor( logging)
            return Retrofit.Builder()
                .client(okkHttpclient.build())
                .baseUrl(Constants.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }

    }



}