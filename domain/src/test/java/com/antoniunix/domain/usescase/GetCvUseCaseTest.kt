package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.ImmediateThreadExecutor
import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCvUseCaseTest {
    @Mock
    private lateinit var cVRepository: CVRepository
    @Mock
    private lateinit var postExecutor: PostExecutionThread

    private lateinit var getCvUseCase: GetCvUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        getCvUseCase = GetCvUseCase(cVRepository, ImmediateThreadExecutor(), postExecutor)
    }

    @Test
    fun getCvUseCase() {
        val cv = fillMainResponseModel()

        Mockito.`when`(cVRepository.getCV()).thenReturn(Single.just(cv))

        val receivedResponse = cVRepository.getCV().test().assertComplete().values()[0]

        val profile = receivedResponse.profile
        val school = receivedResponse.schoolTrajectory?.get(0)
        val professionalTrajectory = receivedResponse.professionalTrajectory?.get(0)
        Assert.assertEquals(profile?.name, cv.profile?.name)
        Assert.assertEquals(profile?.telHome, cv.profile?.telHome)
        Assert.assertEquals(profile?.telCellPhone, cv.profile?.telCellPhone)
        Assert.assertEquals(profile?.profession, cv.profile?.profession)
        Assert.assertEquals(profile?.address, cv.profile?.address)
        Assert.assertEquals(profile?.age, cv.profile?.age)
        Assert.assertEquals(profile?.curp, cv.profile?.curp)
        Assert.assertEquals(profile?.rfc, cv.profile?.rfc)
        Assert.assertEquals(profile?.nationality, cv.profile?.nationality)
        Assert.assertEquals(profile?.photo, cv.profile?.photo)
        Assert.assertEquals(school.institutionName, cv.schoolTrajectory.get(0).institutionName)
        Assert.assertEquals(school.academyLevel, cv.schoolTrajectory.get(0).academyLevel)
        Assert.assertEquals(school.initDate, cv.schoolTrajectory.get(0).initDate)
        Assert.assertEquals(school.finishDate, cv.schoolTrajectory.get(0).finishDate)
        Assert.assertEquals(school.titleObtain, cv.schoolTrajectory.get(0).titleObtain)
        Assert.assertEquals(professionalTrajectory.companyName, cv.professionalTrajectory.get(0).companyName)
        Assert.assertEquals(professionalTrajectory.tel, cv.professionalTrajectory.get(0).tel)
        Assert.assertEquals(professionalTrajectory.sartDate, cv.professionalTrajectory.get(0).sartDate)
        Assert.assertEquals(professionalTrajectory.endDate, cv.professionalTrajectory.get(0).endDate)
        Assert.assertEquals(professionalTrajectory.position, cv.professionalTrajectory.get(0).position)
    }

    private fun fillMainResponseModel(): CV {
        var cv = CV()
        val profile = Profile("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")
        val school = SchoolTrajectory("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")
        val professionalTrajectory = ProfessionalTrajectory("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val schoolTrajectoryList = ArrayList<SchoolTrajectory>()
        val professionalTrajectoryList = ArrayList<ProfessionalTrajectory>()

        schoolTrajectoryList.add(school)
        professionalTrajectoryList.add(professionalTrajectory)

        cv.profile = profile
        cv.professionalTrajectory = professionalTrajectoryList
        cv.schoolTrajectory = schoolTrajectoryList
        return cv
    }
}