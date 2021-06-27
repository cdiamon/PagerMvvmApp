package com.example.companyApp.data.remote

import com.example.companyApp.data.models.responses.ManufacturerResponse
import com.example.companyApp.data.models.responses.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarsApi {

    /**
     * This is the first Endpoint. Calling this API will return A Manufacturers list.
     *
     * @param wa_key Required. API_KEY required for every request
     * @param page Required. Page section the car manufacturers should be fetched from. Starting from 0
     * @param pageSize Required. Number of cars that has to be included in every call (page)
     */
    @GET("/v1/car-types/manufacturer/")
    suspend fun getManufacturers(
        @Query("wa_key") wa_key: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<ManufacturerResponse>

    /**
     * This is the second Endpoint. Calling this API will return A Manufacturer's main type cars.
     * For this API you need a manufacturer id which can be received using Manufacturer API.
     *
     * @param wa_key Required. API_KEY required for every request
     * @param manufacturer Required. Manufacturer id, that we want to get the types for.
     *                     Please note this parameter must be fetched from previous APIs
     */
    @GET("/v1/car-types/main-types/")
    suspend fun getMainTypes(
        @Query("wa_key") wa_key: String,
        @Query("manufacturer") manufacturer: String,
    ): Response<DataResponse>

    /**
     * This is the third and the last Endpoint. Calling this API will return A Car Type's Built-dates.
     * For this API you need a manufacturer id and Main Car Type which must be received using previous APIs.
     *
     * @param wa_key Required. API_KEY required for every request
     * @param manufacturer Required. Manufacturer id, that we want to get the types for.
     *                     Please note this parameter must be fetched from previous APIs
     * @param mainType Required. Car Main Type Id, that we want to get the built-dates for.
     *                 Please note this parameter must be fetched from previous APIs
     */
    @GET("/v1/car-types/built-dates/")
    suspend fun getBuildDates(
        @Query("wa_key") wa_key: String,
        @Query("manufacturer") manufacturer: String,
        @Query("main-type") mainType: String,
    ): Response<DataResponse>
}
