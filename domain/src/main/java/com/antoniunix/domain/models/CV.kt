package com.antoniunix.domain.models

data class CV(
        var profile: Profile? = null,
        var schoolTrajectory: List<SchoolTrajectory> = ArrayList(),
        var professionalTrajectory: List<ProfessionalTrajectory> = ArrayList()
)