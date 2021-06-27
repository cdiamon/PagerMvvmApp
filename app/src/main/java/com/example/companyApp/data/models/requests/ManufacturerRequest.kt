package com.example.companyApp.data.models.requests

import com.example.companyApp.BuildConfig

data class ManufacturerRequest(
    val wa_key: String = BuildConfig.ApiKey,
    val page: Int,
    val pageSize: Int = 15,
)
