package com.example.practicaltestaab.domain.model

import com.google.gson.annotations.SerializedName

data class DcBankResponseItem(
    @SerializedName("bankName")
    var bankName: String,
    @SerializedName("age")
    var age: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("url")
    var url: String,
)