package com.example.runningappvita.model

import com.example.runningappvita.model.baseResponse.DataResponse
import com.example.runningappvita.model.baseResponse.VitaResponse

abstract class BaseResponse<DATA : DataResponse<U>, U>: VitaResponse<DATA>() {

    abstract fun getSource(): U?

    fun a(){
        success
    }
}
