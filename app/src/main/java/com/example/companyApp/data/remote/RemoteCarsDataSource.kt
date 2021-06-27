package com.example.companyApp.data.remote

import com.example.companyApp.data.models.requests.BuildDatesRequest
import com.example.companyApp.data.models.requests.MainTypesRequest
import com.example.companyApp.data.models.requests.ManufacturerRequest
import com.example.companyApp.data.models.responses.ManufacturerResponse
import com.example.companyApp.data.models.responses.DataResponse
import com.example.companyApp.data.models.ResultStatus
import retrofit2.Response
import javax.inject.Inject

class RemoteCarsDataSource @Inject constructor(private val carsApi: CarsApi) : CarsDataStore {

    override suspend fun getManufacturers(searchQuery: ManufacturerRequest): ResultStatus<ManufacturerResponse> {
        return getResponse {
            carsApi.getManufacturers(
                wa_key = searchQuery.wa_key,
                page = searchQuery.page,
                pageSize = searchQuery.pageSize
            )
        }
    }

    override suspend fun getMainTypes(searchQuery: MainTypesRequest): ResultStatus<DataResponse> {
        return getResponse {
            carsApi.getMainTypes(
                wa_key = searchQuery.wa_key,
                manufacturer = searchQuery.manufacturer
            )
        }
    }

    override suspend fun getBuildDates(searchQuery: BuildDatesRequest): ResultStatus<DataResponse> {
        return getResponse {
            carsApi.getBuildDates(
                wa_key = searchQuery.wa_key,
                manufacturer = searchQuery.manufacturer,
                mainType = searchQuery.mainType
            )
        }
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): ResultStatus<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ResultStatus.success(result.body())
            } else {
                ResultStatus.error(result.errorBody().toString())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            ResultStatus.error("Unknown Error", null)
        }
    }
}
