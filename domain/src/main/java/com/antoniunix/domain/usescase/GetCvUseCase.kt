package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.executor.ThreadExecutor
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCvUseCase @Inject constructor(
        private val cvRepository: CVRepository,
        private val executionScheduler: ThreadExecutor,
        private val postExecutionScheduler: PostExecutionThread
):SingleUseCase<Void,CV>(executionScheduler,postExecutionScheduler){

    override fun buildUseCase(params: Void?): Single<CV> {
        return cvRepository.getCV().subscribeOn(Schedulers.from(executionScheduler))
                .observeOn(postExecutionScheduler.getSchedule())
    }

}