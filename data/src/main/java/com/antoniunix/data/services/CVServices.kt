package com.antoniunix.data.services

import com.antoniunix.data.model.response.MainresponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface CVServices{

    @GET(ENDPOINT_CV)
    fun getCV(): Single<MainresponseModel>

    companion object {
        const val ENDPOINT_CV = "cv.json"
        const val BASE_URL =  "https://gist.githubusercontent.com/antoniunix/384d63510d536b52bb9396a8a89bb6ef/raw/d399e2e3c434fb0283d4f2d3dcdab9789cb11dd5/"
    }

}