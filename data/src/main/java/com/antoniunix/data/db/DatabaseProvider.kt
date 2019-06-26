package com.antoniunix.data.db

import io.realm.Realm

interface DatabaseProvider {
    val instance: Realm
}