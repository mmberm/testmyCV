package com.antoniunix.domain.repository

import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import io.reactivex.Single

interface CVRepository {

    fun getCV(): Single<CV>
    fun getProfile(): Single<Profile>
    fun getSchoolTrajectory(): Single<List<SchoolTrajectory>>
    fun getProfessionalTrajectory(): Single<List<ProfessionalTrajectory>>

}