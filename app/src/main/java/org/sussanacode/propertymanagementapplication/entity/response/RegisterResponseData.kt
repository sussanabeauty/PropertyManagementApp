package org.sussanacode.propertymanagementapplication.entity.response

data class RegisterResponseData(
    val data: RegisterData?,
    val error: Boolean,
    val message: String
)

data class RegisterData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val type: String
)