package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.ProfessionalTrajectoryRealm
import com.antoniunix.domain.mapper.Transform
import com.antoniunix.domain.models.ProfessionalTrajectory

class ProfessionalMapper : Transform<List<ProfessionalTrajectoryRealm>, List<ProfessionalTrajectory>>() {

    override fun transform(value: List<ProfessionalTrajectoryRealm>): List<ProfessionalTrajectory> {
        val list: MutableList<ProfessionalTrajectory> = ArrayList()
        value.forEach { list.add(transformProfessionalItem(it)) }
        return list
    }

    private fun transformProfessionalItem(value: ProfessionalTrajectoryRealm): ProfessionalTrajectory {
        val companyName: String = value.companyName ?: ""
        val tel: String = value.tel ?: ""
        val startDate: String = value.startDate ?: ""
        val endDate: String = value.endDate ?: ""
        val position: String = value.position ?: ""
        return ProfessionalTrajectory(
                companyName,
                tel,
                startDate,
                endDate,
                position
        )
    }
}