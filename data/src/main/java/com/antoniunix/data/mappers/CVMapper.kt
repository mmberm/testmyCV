package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.*
import com.antoniunix.domain.mapper.Transform
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory

class CVMapper() : Transform<MainresponseModel, CV>() {

    override fun transform(value: MainresponseModel): CV {
        val profile: Profile = transformProfileFromModel(value.profileModel)
        val schoolTrajectory: List<SchoolTrajectory> = transformSchoolFromModel(value.schoolTrajectoryModel)
        val professionalTrajectory: List<ProfessionalTrajectory> = transformProfessionalFromModel(value.professionalTrajectoryModel)
        return CV(profile, schoolTrajectory, professionalTrajectory)
    }


    private fun transformProfileFromModel(value: ProfileModel?): Profile {

        val name: String = value?.name ?: ""
        val telHome: String = value?.telHome ?: ""
        val telCellPhone: String = value?.telCellPhone ?: ""
        val profession: String = value?.profession ?: ""
        val address: String = value?.address ?: ""

        return Profile(
                name,
                telHome,
                telCellPhone,
                profession,
                address
        )
    }

    private fun transformSchoolFromModel(value: List<SchoolTrajectoryModel>): List<SchoolTrajectory> {
        val list: MutableList<SchoolTrajectory> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: SchoolTrajectoryModel): SchoolTrajectory {
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

    private fun transformProfessionalFromModel(value: List<ProfessionalTrajectoryModel>): List<ProfessionalTrajectory> {
        val list: MutableList<ProfessionalTrajectory> = ArrayList()
        value.forEach { list.add(transformProfessionalItem(it)) }
        return list
    }

    private fun transformProfessionalItem(value: ProfessionalTrajectoryModel): ProfessionalTrajectory {
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