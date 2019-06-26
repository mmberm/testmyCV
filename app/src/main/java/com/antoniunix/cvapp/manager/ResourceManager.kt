package com.antoniunix.cvapp.manager

import android.content.res.Resources
import com.antoniunix.cvapp.R
import com.antoniunix.domain.manager.ResourceManagerError

class ResourceManager(private val resources: Resources) : ResourceManagerError {
    override fun getRealmExceptionMessage(): String {
        return resources.getString(R.string.realm_error)
    }

    override fun getConnectionErrorMessage(): String {
        return resources.getString(R.string.connection_error)
    }

    override fun getTimeoutExceptionMessage(): String {
        return resources.getString(R.string.timeout_error)
    }

    override fun getGenericExceptionMessage(): String {
        return resources.getString(R.string.generic_error)
    }

}