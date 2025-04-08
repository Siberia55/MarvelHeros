package com.example.marvelheros.data.model

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)