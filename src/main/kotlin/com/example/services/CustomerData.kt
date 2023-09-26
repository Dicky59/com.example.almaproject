package com.example.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.json.JSONObject

@Serializable
data class Customer(
    val id: Long,
    var name: String,
    var email: String?,
    var homepage: String?,
    var businessId: String?,
    var streetAddress: String?,
    var phone: String?
)


fun getCustomerData(businessId: String?) {
    runBlocking {
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
        val phone = json.getJSONObject("phone").getString("value")
        val streetAddress = json.getJSONObject("streetAddress").getJSONObject("street").getString("value")
        val postalCode = json.getJSONObject("streetAddress").getJSONObject("postalCode").getString("value")
        val city = json.getJSONObject("streetAddress").getJSONObject("city").getString("value")

        println(phone)
        println("$streetAddress, $postalCode $city")

//        val response: HttpResponse = client.post("http://localhost:8080/customer") {
//            contentType(ContentType.Application.Json)
//            setBody(Customer(3, "Jet", "Brains"))
//        }
//        println(response.bodyAsText())
//
//        val customer: Customer = client.get("http://localhost:8080/customer/3").body()
//        println("First name: '${customer.firstName}', last name: '${customer.lastName}'")
    }

}
