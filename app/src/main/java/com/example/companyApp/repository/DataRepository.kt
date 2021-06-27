package com.example.companyApp.repository

import com.example.companyApp.data.models.requests.BuildDatesRequest
import com.example.companyApp.data.models.requests.MainTypesRequest
import com.example.companyApp.data.models.requests.ManufacturerRequest
import com.example.companyApp.data.models.vo.ManufacturerVO
import com.example.companyApp.data.models.vo.DataVO
import com.example.companyApp.data.models.vo.toVo
import com.example.companyApp.data.remote.CarsDataStore
import com.example.companyApp.data.models.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface DataRepository {

    fun loadManufacturers(page: Int): Flow<ResultStatus<ManufacturerVO>>
    fun loadMainTypes(manufacturer: String): Flow<ResultStatus<DataVO>>
    fun loadBuildDates(manufacturer: String, mainType: String): Flow<ResultStatus<DataVO>>
}

class DataRepositoryImpl(private val dataSource: CarsDataStore) : DataRepository {


    override fun loadManufacturers(page: Int): Flow<ResultStatus<ManufacturerVO>> {
        return flow {
            emit(ResultStatus.loading())
            emit(dataSource.getManufacturers(ManufacturerRequest(page = page)).toVo())
        }.flowOn(Dispatchers.IO)
    }

    override fun loadMainTypes(manufacturer: String): Flow<ResultStatus<DataVO>> {
        return flow {
            emit(ResultStatus.loading())
            emit(dataSource.getMainTypes(MainTypesRequest(manufacturer = manufacturer)).toVo())
        }.flowOn(Dispatchers.IO)
    }

    override fun loadBuildDates(manufacturer: String, mainType: String): Flow<ResultStatus<DataVO>> {
        return flow {
            emit(ResultStatus.loading())
            emit(dataSource.getBuildDates(BuildDatesRequest(manufacturer = manufacturer, mainType = mainType)).toVo())
        }.flowOn(Dispatchers.IO)
    }
}
