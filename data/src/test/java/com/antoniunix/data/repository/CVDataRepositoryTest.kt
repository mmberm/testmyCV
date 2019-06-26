package com.antoniunix.data.repository

import com.antoniunix.data.db.RealmSource
import com.antoniunix.data.mappers.CVMapper
import com.antoniunix.data.mappers.ProfessionalMapper
import com.antoniunix.data.mappers.ProfileMapper
import com.antoniunix.data.mappers.SchoolMapper
import com.antoniunix.data.model.response.*
import com.antoniunix.data.services.CVServices
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import com.antoniunix.domain.usescase.GetProfileUseCase
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.SocketTimeoutException
import org.mockito.Mockito.`when` as whenever

class CVDataRepositoryTest {

    @Mock
    private lateinit var realmSource: RealmSource
    @Mock
    private lateinit var cvServices: CVServices

    private lateinit var cvRepository: CVRepository
    private val mapperCV = CVMapper()
    private val mapperProfile = ProfileMapper()
    private val schoolMapper = SchoolMapper()
    private val professionalMapper=ProfessionalMapper()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        cvRepository = CVDataRepository(cvServices, realmSource)
    }

    @Test
    fun getCVTest() {
        var response = MainresponseModel()
        val profileModel = ProfileModel("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")
        val schoolTrajectoryModel = SchoolTrajectoryModel("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")
        val professionalTrajectoryModel = ProfessionalTrajectoryModel("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val schoolTrajectoryList = ArrayList<SchoolTrajectoryModel>()
        val professionalTrajectoryList = ArrayList<ProfessionalTrajectoryModel>()

        schoolTrajectoryList.add(schoolTrajectoryModel)
        professionalTrajectoryList.add(professionalTrajectoryModel)

        response.profileModel = profileModel
        response.professionalTrajectoryModel = professionalTrajectoryList
        response.schoolTrajectoryModel = schoolTrajectoryList

        whenever(cvServices.getCV()).thenReturn(Single.just(response))

        val receivedValues = cvRepository.getCV()
                .test()
                .assertResult(mapperCV.transform(response))
                .values()
        Assert.assertEquals(mapperCV.transform(response), receivedValues[0])
    }

    @Test
    fun testGetInformationTimeoutError() {
        whenever(cvServices.getCV()).thenReturn(Single.error(SocketTimeoutException()))
        cvRepository.getCV()
                .test()
                .assertFailure(GetProfileUseCase.CatalogException.TimeoutException::class.java)
    }

    @Test
    fun getProfileTest() {
        val profileRealm = ProfileRealm("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")

        val profile = Profile("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")

        whenever(realmSource.getProfile()).thenReturn(Single.just(profile))

        val receivedValues = cvRepository.getProfile()
                .test()
                .assertResult(mapperProfile.transform(profileRealm))
                .values()
        Assert.assertEquals(profile, receivedValues[0])
    }

    @Test
    fun getSchoolTrajectoryTest() {
        val schoolTrajectory = SchoolTrajectory("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")

        val schoolTrajectoryReal = SchoolTrajectoryRealm("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")

        val schoolTrajectoryList = ArrayList<SchoolTrajectory>()
        val schoolTrajectoryRealList = ArrayList<SchoolTrajectoryRealm>()
        schoolTrajectoryList.add(schoolTrajectory)
        schoolTrajectoryRealList.add(schoolTrajectoryReal)


        whenever(realmSource.getSchoolTrajectory()).thenReturn(Single.just(schoolTrajectoryList))

        val receivedValues = cvRepository.getSchoolTrajectory()
                .test()
                .assertResult(schoolMapper.transform(schoolTrajectoryRealList))
                .values()
        Assert.assertEquals(schoolTrajectoryList, receivedValues[0])
    }

    @Test
    fun getProfessionalTrajectoryTest() {
        val professionalTrajectory = ProfessionalTrajectory("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val professionalTrajectoryRealm = ProfessionalTrajectoryRealm("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val professionalTrajectoryList = ArrayList<ProfessionalTrajectory>()
        val professionalTrajectoryRealList = ArrayList<ProfessionalTrajectoryRealm>()
        professionalTrajectoryList.add(professionalTrajectory)
        professionalTrajectoryRealList.add(professionalTrajectoryRealm)


        whenever(realmSource.getProfessional()).thenReturn(Single.just(professionalTrajectoryList))

        val receivedValues = cvRepository.getProfessionalTrajectory()
                .test()
                .assertResult(professionalMapper.transform(professionalTrajectoryRealList))
                .values()
        Assert.assertEquals(professionalTrajectoryList, receivedValues[0])
    }


}