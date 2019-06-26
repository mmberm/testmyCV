package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.executor.ThreadExecutor
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProfileUseCase @Inject constructor(
        private val cvRepository: CVRepository,
        private val executionScheduler: ThreadExecutor,
        private val postExecutionScheduler: PostExecutionThread
):SingleUseCase<Void, Profile>(executionScheduler,postExecutionScheduler){



    override fun buildUseCase(params: Void?): Single<Profile> {
        return cvRepository.getProfile().subscribeOn(Schedulers.from(executionScheduler))
                .observeOn(postExecutionScheduler.getSchedule())
    }

    sealed class CatalogException {
        class ConnectionError : Exception()
        class TimeoutException : Exception()
        class GenericError : Exception()
    }

}