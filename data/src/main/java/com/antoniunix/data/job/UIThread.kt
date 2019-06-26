package com.antoniunix.data.job

import com.antoniunix.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override fun getSchedule(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}