package com.example.testapp.data.db.converters

import androidx.room.TypeConverter
import com.example.testapp.data.db.entities.user.Address
import com.example.testapp.data.db.entities.user.Company
import com.example.testapp.data.db.entities.user.Geo
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun companyToString(company: Company): String = Gson().toJson(company)
    @TypeConverter
    fun stringToCompany(string: String): Company = Gson().fromJson(string, Company::class.java)

    @TypeConverter
    fun addressToString(company: Address): String = Gson().toJson(company)
    @TypeConverter
    fun stringToAddress(string: String): Address = Gson().fromJson(string, Address::class.java)


    @TypeConverter
    fun geoToString(company: Geo): String = Gson().toJson(company)
    @TypeConverter
    fun stringToGeo(string: String): Geo = Gson().fromJson(string, Geo::class.java)
}