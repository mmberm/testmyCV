package com.antoniunix.data.db

import io.realm.Realm


class RealmProvider:DatabaseProvider{
    override val instance: Realm
        get() = Realm.getDefaultInstance()

}