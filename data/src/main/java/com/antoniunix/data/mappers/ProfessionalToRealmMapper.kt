package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.ProfessionalTrajectoryModel
import com.antoniunix.data.model.response.ProfessionalTrajectoryRealm
import com.antoniunix.domain.mapper.Transform


class ProfessionalToRealmMapper : Transform<List<ProfessionalTrajectoryModel>, List<ProfessionalTrajectoryRealm>>() {

    override fun transform(value: List<ProfessionalTrajectoryModel>): List<ProfessionalTrajectoryRealm> {
        val list: MutableList<ProfessionalTrajectoryRealm> = ArrayList()
        value.forEach { list.add(transformProfessionalItem(it)) }
        return list
    }

    private fun transformProfessionalItem(value: ProfessionalTrajectoryModel): ProfessionalTrajectoryRealm {
        val companyName: String = value.companyName ?: ""
        val tel: String = value.tel ?: ""
        val startDate: String = value.startDate ?: ""
        val endDate: String = value.endDate ?: ""
        val position: String = value.position ?: ""
        return ProfessionalTrajectoryRealm(
                companyName,
                tel,
                startDate,
                endDate,
                position
        )
    }
}
