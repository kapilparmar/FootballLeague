package com.coveiot.android.footballleague.network

import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SportErrorModel : SportsApiErrorModel, Serializable {
    @SerializedName("message")
    var msg: String
        private set

    constructor(msg: String) : super(-1) {
        this.msg = msg
    }

    constructor(msg: String, code: Int) : super(code) {
        this.msg = msg
    }
}