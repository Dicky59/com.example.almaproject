package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class CustomerRequest(
    var name: String,
    var email: String?,
    var homepage: String?,
    var businessId: String?,
    var streetAddress: String?,
    var phone: String?
)
