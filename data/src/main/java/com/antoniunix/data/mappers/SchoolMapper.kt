package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.SchoolTrajectoryRealm
import com.antoniunix.domain.mapper.Transform
import com.antoniunix.domain.models.SchoolTrajectory

class SchoolMapper : Transform<List<SchoolTrajectoryRealm>, List<SchoolTrajectory>>() {

    override fun transform(value: List<SchoolTrajectoryRealm>): List<SchoolTrajectory> {
        val list: MutableList<SchoolTrajectory> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: SchoolTrajectoryRealm): SchoolTrajectory {
        val institutionName: String = value.institutionName ?: ""
        val academyLevel: String = value.academyLevel ?: ""
        val initDate: String = value.initDate ?: ""
        val finishDate: String = value.finishDate ?: ""
        val titleObtain: String = value.titleObtain ?: ""
        return SchoolTrajectory(
                institutionName,
                academyLevel,
                initDate,
                finishDate,
                titleObtain
        )
    }


}