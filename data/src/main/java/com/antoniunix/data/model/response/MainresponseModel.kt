package com.antoniunix.data.model.response

data class MainresponseModel(
        var profileModel: ProfileModel? = null,
        var schoolTrajectoryModel: List<SchoolTrajectoryModel> = ArrayList(),
        var professionalTrajectoryModel: List<ProfessionalTrajectoryModel> = ArrayList()
)