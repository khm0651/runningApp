package com.example.runningappvita.model.baseResponse

import com.google.gson.annotations.SerializedName

abstract class VitaResponse<DATA> {
    @SerializedName("success")
    var success = false

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: DATA? = null

    @SerializedName("metadata")
    var metaData: MetaResponse? = null
    fun hasData(): Boolean {
        return data != null
    }
}
