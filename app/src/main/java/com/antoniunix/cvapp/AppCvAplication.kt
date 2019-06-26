package com.antoniunix.cvapp

import android.support.multidex.MultiDexApplication
import com.antoniunix.cvapp.di.AppComponent
import com.antoniunix.cvapp.di.AppModule
import com.antoniunix.cvapp.di.DaggerAppComponent
import com.antoniunix.cvapp.di.DataModule
import com.antoniunix.data.network.Networkmodule
import com.antoniunix.data.services.CVServices
import io.realm.Realm

class AppCvAplication : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .networkmodule(Networkmodule(CVServices.BASE_URL))
                .build()
    }
}