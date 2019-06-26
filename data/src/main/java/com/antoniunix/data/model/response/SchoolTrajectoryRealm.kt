package com.antoniunix.data.model.response

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SchoolTrajectoryRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var institutionName: String? = null
    var academyLevel: String? = null
    var initDate: String? = null
    var finishDate: String? = null
    var titleObtain: String? = null

    constructor(institutionName: String, academyLevel: String, initDate: String, finishDate: String, titleObtain: String) : this() {
        this.institutionName = institutionName
        this.academyLevel = academyLevel
        this.initDate = initDate
        this.finishDate = finishDate
        this.titleObtain = titleObtain
    }
}