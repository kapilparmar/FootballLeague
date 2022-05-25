package com.coveiot.android.footballleagues.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class  SportsApiErrorModel(val code: Int): Serializable {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("response")
    var response: String? = null


}