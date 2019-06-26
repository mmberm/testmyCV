package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.ProfileModel
import com.antoniunix.data.model.response.ProfileRealm
import com.antoniunix.domain.mapper.Transform

class ProfileToRealmMapper : Transform<ProfileModel?, ProfileRealm>() {

    override fun transform(value: ProfileModel?): ProfileRealm {

        val name: String = value?.name ?: ""
        val telHome: String = value?.telHome ?: ""
        val telCellPhone: String = value?.telCellPhone ?: ""
        val profession: String = value?.profession ?: ""
        val address: String = value?.address ?: ""
        val age: String = value?.age?: ""
        val curp: String = value?.curp?: ""
        val rfc: String = value?.rfc?: ""
        val nationality: String = value?.nationality?: ""
        val photo: String = value?.photo?: ""

        return ProfileRealm(
                name,
                telHome,
                telCellPhone,
                profession,
                address,
                age,
                curp,
                rfc,
                nationality,
                photo
        )
    }

}