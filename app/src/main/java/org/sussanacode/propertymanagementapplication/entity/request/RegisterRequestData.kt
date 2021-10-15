package org.sussanacode.propertymanagementapplication.entity.request

data class RegisterRequestData(
    val email: String,
    val name: String,
    val password: String,
    val type: String,
    val landlordEmail: String?
)