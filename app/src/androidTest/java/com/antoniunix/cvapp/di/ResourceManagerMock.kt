package com.antoniunix.cvapp.di

import com.antoniunix.domain.manager.ResourceManagerError

class ResourceManagerMock :ResourceManagerError{
    override fun getConnectionErrorMessage(): String {
        return ""
    }

    override fun getTimeoutExceptionMessage(): String {
        return ""
    }

    override fun getGenericExceptionMessage(): String {
        return ""
    }

    override fun getRealmExceptionMessage(): String {
        return ""
    }

}