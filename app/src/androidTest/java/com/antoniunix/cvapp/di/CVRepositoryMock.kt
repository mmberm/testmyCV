package com.antoniunix.cvapp.di

import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single

class CVRepositoryMock(private val cv: CV) : CVRepository {
    override fun getCV(): Single<CV> {
        return Single.just(cv)
    }

    override fun getProfile(): Single<Profile> {
        return Single.just(cv.profile)
    }

    override fun getSchoolTrajectory(): Single<List<SchoolTrajectory>> {
        return Single.just(cv.schoolTrajectory)
    }

    override fun getProfessionalTrajectory(): Single<List<ProfessionalTrajectory>> {
        return Single.just(cv.professionalTrajectory)
    }

}