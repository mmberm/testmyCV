package com.antoniunix.data.db

import com.antoniunix.data.model.response.ProfessionalTrajectoryModel
import com.antoniunix.data.model.response.ProfileModel
import com.antoniunix.data.model.response.SchoolTrajectoryModel
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import io.reactivex.Single

interface RealmSource {

    fun saveProfile(profileModel: ProfileModel)
    fun saveSchoolTrajectory(schoolTrajectoryModel: List<SchoolTrajectoryModel>)
    fun saveProfessionalTrajectory(professionalTrajectoryModel: List<ProfessionalTrajectoryModel>)

    fun getProfile(): Single<Profile>
    fun getSchoolTrajectory(): Single<List<SchoolTrajectory>>
    fun getProfessional(): Single<List<ProfessionalTrajectory>>

}