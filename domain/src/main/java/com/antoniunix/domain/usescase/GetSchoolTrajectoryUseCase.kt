package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.executor.ThreadExecutor
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSchoolTrajectoryUseCase @Inject constructor(
        private val cvRepository: CVRepository,
        private val executionScheduler: ThreadExecutor,
        private val postExecutionScheduler: PostExecutionThread
):SingleUseCase<Void, List<SchoolTrajectory>>(executionScheduler,postExecutionScheduler){

    override fun buildUseCase(params: Void?): Single<List<SchoolTrajectory>> {
        return cvRepository.getSchoolTrajectory().subscribeOn(Schedulers.from(executionScheduler))
                .observeOn(postExecutionScheduler.getSchedule())
    }


}