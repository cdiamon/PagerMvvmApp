package com.example.companyApp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.companyApp.data.models.vo.ManufacturerVO
import com.example.companyApp.data.models.vo.DataVO
import com.example.companyApp.repository.DataRepository
import com.example.companyApp.data.models.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarSearchViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    var manufacturer: Pair<String, String>? = null
    var mainType: Pair<String, String>? = null
    var buildDate: Pair<String, String>? = null

    private val _manufacturers: MutableLiveData<ResultStatus<ManufacturerVO>> =
        dataRepository
            .loadManufacturers(0)
            .asLiveData(viewModelScope.coroutineContext) as MutableLiveData
    val manufacturers: LiveData<ResultStatus<ManufacturerVO>>
        get() = _manufacturers

    private val _mansList = MutableLiveData<List<Pair<String, String>>>()
    val mansList: LiveData<List<Pair<String, String>>>
        get() = _mansList

    private val _mainTypes = MutableLiveData<ResultStatus<DataVO>>()
    val mainTypes: LiveData<ResultStatus<DataVO>>
        get() = _mainTypes

    private val _typesList = MutableLiveData<List<Pair<String, String>>>()
    val typesList: LiveData<List<Pair<String, String>>>
        get() = _typesList

    private val _buildDates = MutableLiveData<ResultStatus<DataVO>>()
    val buildDates: LiveData<ResultStatus<DataVO>>
        get() = _buildDates

    private val manufacturersCache = mutableListOf<Pair<String, String>>()

    fun addToCache(list: List<Pair<String, String>>) {
        manufacturersCache.addAll(list)
        _mansList.value = manufacturersCache
    }

    private fun fetchManufacturers(page: Int = 0) {
        viewModelScope.launch {
            dataRepository
                .loadManufacturers(page)
                .collect {
                    _manufacturers.value = it
                }
        }
    }

    fun fetchNextManufacturers() {
        viewModelScope.launch {
            _manufacturers.value?.data?.let {
                if (it.page < it.totalPageCount) fetchManufacturers(it.page + 1)
            }
        }
    }

    fun fetchMainTypes() {
        manufacturer?.let {
            viewModelScope.launch {
                dataRepository
                    .loadMainTypes(manufacturer!!.first)
                    .collect {
                        _mainTypes.value = it
                    }
            }
        }
    }

    fun fetchBuildDates() {
        manufacturer?.let {
            viewModelScope.launch {
                dataRepository
                    .loadBuildDates(manufacturer!!.first, mainType!!.first)
                    .collect {
                        _buildDates.value = it
                    }
            }
        }
    }

    fun setFilterState(query: String) {
        _mainTypes.value?.data?.data?.let { oldList ->
            _typesList.value = oldList
                .filter { it.second.lowercase().contains(query) }
        }
    }
}
