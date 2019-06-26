package com.antoniunix.domain.manager

interface ResourceManagerError{
    fun getConnectionErrorMessage(): String
    fun getTimeoutExceptionMessage(): String
    fun getGenericExceptionMessage(): String
    fun getRealmExceptionMessage(): String
}