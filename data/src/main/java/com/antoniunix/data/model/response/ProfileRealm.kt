package com.antoniunix.data.model.response

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ProfileRealm():RealmObject(){
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String ?=null
    var telHome: String ?=null
    var telCellPhone: String ?=null
    var profession: String ?=null
    var address: String ?=null
    var age: String? = null
    var curp: String? = null
    var rfc: String? = null
    var nationality: String? = null
    var photo: String? = null



    constructor(name: String,
                telHome: String,
                telCellPhone: String,
                profession: String,
                address: String,
                age: String,
                curp: String,
                rfc: String,
                nationality: String,
                photo: String):this(){
        this.name=name
        this.telHome=telHome
        this.telCellPhone=telCellPhone
        this.profession=profession
        this.address=address
        this.age=age
        this.curp=curp
        this.rfc=rfc
        this.nationality=nationality
        this.photo=photo
    }
}