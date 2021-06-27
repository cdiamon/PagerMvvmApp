package com.example.companyApp.data.models.requests

import com.example.companyApp.BuildConfig

data class BuildDatesRequest(
    val wa_key: String = BuildConfig.ApiKey,
    val manufacturer: String,
    val mainType: String,
)
