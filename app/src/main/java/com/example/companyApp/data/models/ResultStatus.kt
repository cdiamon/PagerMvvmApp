package com.example.companyApp.data.models

/**
 * Generic class for holding success response, error response and loading status
 */
data class ResultStatus<out T>(
    val status: Status,
    val data: T? = null,
    val error: Error? = null,
    val message: String? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResultStatus<T> {
            return ResultStatus(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: Error? = null): ResultStatus<T> {
            return ResultStatus(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): ResultStatus<T> {
            return ResultStatus(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}
