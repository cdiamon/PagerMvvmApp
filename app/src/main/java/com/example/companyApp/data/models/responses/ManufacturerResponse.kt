package com.example.companyApp.data.models.responses

data class ManufacturerResponse(
    val page: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val data: Map<String, String>,
)
