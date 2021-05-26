package com.example.runningappvita.model.baseResponse

abstract class DataResponse<T> {
    abstract fun getData(): T
}
