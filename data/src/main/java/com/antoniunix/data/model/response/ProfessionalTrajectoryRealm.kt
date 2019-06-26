package com.antoniunix.data.model.response

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ProfessionalTrajectoryRealm() : RealmObject() {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var companyName: String ?=null
    var tel: String ?=null
    var startDate: String ?=null
    var endDate: String ?=null
    var position: String ?=null

    constructor(companyName: String, tel: String, startDate: String, endDate: String, position: String) : this() {
        this.companyName = companyName
        this.tel = tel
        this.startDate = startDate
        this.endDate = endDate
        this.position = position
    }


}