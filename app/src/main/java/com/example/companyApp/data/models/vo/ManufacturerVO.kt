package com.example.companyApp.data.models.vo

import com.example.companyApp.data.models.responses.ManufacturerResponse
import com.example.companyApp.data.models.ResultStatus

data class ManufacturerVO(
    val page: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val data: List<Pair<String, String>>,
)

fun ResultStatus<ManufacturerResponse>.toVo(): ResultStatus<ManufacturerVO> {
    return ResultStatus(
        status = status,
        error = error,
        message = message,
        data = if (this.data != null) {
            with(this.data) {
                ManufacturerVO(
                    page = page,
                    pageSize = pageSize,
                    totalPageCount = totalPageCount,
                    data = data.toList()
                )
            }
        } else null
    )
}
