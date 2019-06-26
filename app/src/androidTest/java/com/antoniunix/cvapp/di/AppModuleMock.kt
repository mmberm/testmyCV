package com.antoniunix.cvapp.di

import com.antoniunix.domain.manager.ResourceManagerError
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.repository.CVRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleMock(private val cv: CV) {


    @Provides
    @Singleton
    fun providesRepository(): CVRepository {
        return CVRepositoryMock(cv)
    }

    @Provides
    @Singleton
    fun providesResourceManager(): ResourceManagerError {
        return ResourceManagerMock()
    }


}