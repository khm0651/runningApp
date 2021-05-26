package com.example.runningappvita.model.baseResponse

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("total") var total: Int = 0,
    @SerializedName("count") var count:Int = 0,
    @SerializedName("offset") var offset:Int = 0
)
