package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.SchoolTrajectoryModel
import com.antoniunix.data.model.response.SchoolTrajectoryRealm
import com.antoniunix.domain.mapper.Transform

class SchoolToRealmMapper : Transform<List<SchoolTrajectoryModel>, List<SchoolTrajectoryRealm>>() {

    override fun transform(value: List<SchoolTrajectoryModel>): List<SchoolTrajectoryRealm> {
        val list: MutableList<SchoolTrajectoryRealm> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: SchoolTrajectoryModel): SchoolTrajectoryRealm {
        val institutionName: String = value.institutionName ?: ""
        val academyLevel: String = value.academyLevel ?: ""
        val initDate: String = value.initDate ?: ""
        val finishDate: String = value.finishDate ?: ""
        val titleObtain: String = value.titleObtain ?: ""
        return SchoolTrajectoryRealm(
                institutionName,
                academyLevel,
                initDate,
                finishDate,
                titleObtain
        )
    }


}