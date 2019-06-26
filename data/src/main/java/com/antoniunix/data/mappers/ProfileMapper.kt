package com.antoniunix.data.mappers

import com.antoniunix.data.model.response.ProfileRealm
import com.antoniunix.domain.mapper.Transform
import com.antoniunix.domain.models.Profile

class ProfileMapper : Transform<ProfileRealm, Profile>() {

    override fun transform(value: ProfileRealm): Profile {

        val name: String = value.name ?: ""
        val telHome: String = value.telHome ?: ""
        val telCellPhone: String = value.telCellPhone ?: ""
        val profession: String = value.profession ?: ""
        val address: String = value.address ?: ""
        val age: String = value.age?: ""
        val curp: String = value.curp?: ""
        val rfc: String = value.rfc?: ""
        val nationality: String = value.nationality?: ""
        val photo: String = value.photo?: ""

        return Profile(
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