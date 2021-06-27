package com.example.companyApp.data.models.vo

import com.example.companyApp.data.models.responses.DataResponse
import com.example.companyApp.data.models.ResultStatus

data class DataVO(
    val data: List<Pair<String, String>>,
)

fun ResultStatus<DataResponse>.toVo(): ResultStatus<DataVO> {
    return ResultStatus(
        status = status,
        error = error,
        message = message,
        data = if (this.data != null) {
            with(this.data) {
                DataVO(
                    data = data.toList()
                )
            }
        } else null
    )
}
