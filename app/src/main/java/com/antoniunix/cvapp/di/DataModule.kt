package com.antoniunix.cvapp.di

import com.antoniunix.data.db.DatabaseProvider
import com.antoniunix.data.db.RealmDataSource
import com.antoniunix.data.db.RealmSource
import com.antoniunix.data.db.RealmProvider
import com.antoniunix.data.job.JobExecutor
import com.antoniunix.data.job.UIThread
import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule{

    @Provides
    @Singleton
    fun getJobExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

    @Provides
    @Singleton
    fun providesDatabaseProvider(): DatabaseProvider {
        return RealmProvider()
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(realmProvider: DatabaseProvider): RealmSource {
        return RealmDataSource(realmProvider)
    }

}