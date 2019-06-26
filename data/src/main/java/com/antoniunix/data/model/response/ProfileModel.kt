package com.antoniunix.data.model.response

import com.google.gson.annotations.SerializedName

data class ProfileModel(
        val name: String? = null,
        val telHome: String? = null,
        val telCellPhone: String? = null,
        val profession: String? = null,
        val address: String? = null,
        val age: String? = null,
        val curp: String? = null,
        @SerializedName("RFC")
        val rfc: String? = null,
        val nationality: String? = null,
        val photo: String? = null
)