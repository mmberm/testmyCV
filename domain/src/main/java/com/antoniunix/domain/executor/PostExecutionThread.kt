package com.antoniunix.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getSchedule(): Scheduler
}