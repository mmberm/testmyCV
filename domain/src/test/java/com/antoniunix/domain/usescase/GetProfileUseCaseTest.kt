package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.ImmediateThreadExecutor
import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetProfileUseCaseTest{

    @Mock
    private lateinit var cVRepository: CVRepository
    @Mock
    private lateinit var postExecutor: PostExecutionThread

    private lateinit var getProfileUseCase: GetProfileUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        getProfileUseCase = GetProfileUseCase(cVRepository, ImmediateThreadExecutor(), postExecutor)
    }

    @Test
    fun getCvUseCase() {
        val cv = fillMainResponseModel()

        Mockito.`when`(cVRepository.getProfile()).thenReturn(Single.just(cv))

        val profile = cVRepository.getProfile().test().assertComplete().values()[0]

        Assert.assertEquals(profile?.name, cv.name)
        Assert.assertEquals(profile?.telHome, cv.telHome)
        Assert.assertEquals(profile?.telCellPhone, cv.telCellPhone)
        Assert.assertEquals(profile?.profession, cv.profession)
        Assert.assertEquals(profile?.address, cv.address)
        Assert.assertEquals(profile?.age, cv.age)
        Assert.assertEquals(profile?.curp, cv.curp)
        Assert.assertEquals(profile?.rfc, cv.rfc)
        Assert.assertEquals(profile?.nationality, cv.nationality)
        Assert.assertEquals(profile?.photo, cv.photo)
    }

    private fun fillMainResponseModel(): Profile {
        return Profile("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")
    }

}