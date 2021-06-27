package com.example.companyApp.data.remote

import com.example.companyApp.data.models.requests.BuildDatesRequest
import com.example.companyApp.data.models.requests.MainTypesRequest
import com.example.companyApp.data.models.requests.ManufacturerRequest
import com.example.companyApp.data.models.responses.ManufacturerResponse
import com.example.companyApp.data.models.responses.DataResponse
import com.example.companyApp.data.models.ResultStatus

interface CarsDataStore {
    suspend fun getManufacturers(searchQuery: ManufacturerRequest): ResultStatus<ManufacturerResponse>

    suspend fun getMainTypes(searchQuery: MainTypesRequest): ResultStatus<DataResponse>

    suspend fun getBuildDates(searchQuery: BuildDatesRequest): ResultStatus<DataResponse>
}
