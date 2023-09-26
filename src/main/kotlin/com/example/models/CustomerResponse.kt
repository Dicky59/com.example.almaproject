package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class CustomerResponse(
    var id: Long,
    var name: String,
    var email: String?, // tarkistetaan
    var homepage: String?,
    var businessId: String?,
    var streetAddress: String?,
    var phone: String? //+358
)
