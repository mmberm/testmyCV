package com.antoniunix.cvapp.di

import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DataModuleMock{

    @Provides
    @Singleton
    fun getJobExecutor(): ThreadExecutor {
        return object: ThreadExecutor {
            override fun execute(runnable: Runnable?) {
                runnable?.run()
            }
        }
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(): PostExecutionThread {
        return object: PostExecutionThread {
            override fun getSchedule(): Scheduler {
                return Schedulers.trampoline()
            }
        }
    }

}