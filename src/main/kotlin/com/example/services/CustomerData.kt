package com.example.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.json.JSONObject


suspend fun getCustomerPhone(businessId: String): String? {
    if (businessId != "") {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        val jsonString: String? = client.get(
            "https://www.kauppalehti.fi/company-api/basic-info/$businessId"
        ).body()

        val json = JSONObject(jsonString)
        val phoneData = json.getJSONObject("phone").getString("value") // put this to fun createCustomer

        return(phoneData)

    }else{
        return null
    }
}

suspend fun getCustomerAddress(businessId: String): String? {
    if (businessId != "") {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        val jsonString: String? = client.get(
            "https://www.kauppalehti.fi/company-api/basic-info/$businessId"
        ).body()

        val json = JSONObject(jsonString)
        val streetAddressData = json.getJSONObject("streetAddress").getJSONObject("street").getString("value") // put this to fun createCustomer
        val postalCode = json.getJSONObject("streetAddress").getJSONObject("postalCode").getString("value")
        val city = json.getJSONObject("streetAddress").getJSONObject("city").getString("value")

        return("$streetAddressData, $postalCode $city")

    }else{
        return null
    }
}
