package com.example.companyApp.data.models.requests

import com.example.companyApp.BuildConfig

data class MainTypesRequest(
    val wa_key: String = BuildConfig.ApiKey,
    val manufacturer: String,
)
