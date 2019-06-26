package com.antoniunix.cvapp.di

import android.content.Context
import android.content.res.Resources
import com.antoniunix.cvapp.manager.ResourceManager
import com.antoniunix.data.db.RealmDataSource
import com.antoniunix.data.db.RealmSource
import com.antoniunix.data.repository.CVDataRepository
import com.antoniunix.data.services.CVServices
import com.antoniunix.domain.manager.ResourceManagerError
import com.antoniunix.domain.repository.CVRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesAppContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providesResources(context: Context): Resources {
        return context.resources
    }


    @Provides
    @Singleton
    fun providesResumeRepository(cvServices: CVServices,
                                 realmSource: RealmSource): CVRepository {
        return CVDataRepository(cvServices, realmSource)
    }

    @Provides
    @Singleton
    fun providesResourceManager(resources: Resources): ResourceManagerError {
        return ResourceManager(resources)
    }

}