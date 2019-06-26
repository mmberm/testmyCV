package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.ImmediateThreadExecutor
import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetSchoolTrajectoryUseCaseTest {
    @Mock
    private lateinit var cVRepository: CVRepository
    @Mock
    private lateinit var postExecutor: PostExecutionThread

    private lateinit var getSchoolUseCase: GetSchoolTrajectoryUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        getSchoolUseCase = GetSchoolTrajectoryUseCase(cVRepository, ImmediateThreadExecutor(), postExecutor)
    }

    @Test
    fun getCvUseCase() {
        val cv = fillMainResponseModel()

        Mockito.`when`(cVRepository.getSchoolTrajectory()).thenReturn(Single.just(cv))

        val receivedResponse = cVRepository.getSchoolTrajectory().test().assertComplete().values()[0]

        val school = receivedResponse.get(0)
        Assert.assertEquals(school.institutionName, cv.get(0).institutionName)
        Assert.assertEquals(school.academyLevel, cv.get(0).academyLevel)
        Assert.assertEquals(school.initDate, cv.get(0).initDate)
        Assert.assertEquals(school.finishDate, cv.get(0).finishDate)
        Assert.assertEquals(school.titleObtain, cv.get(0).titleObtain)
    }

    private fun fillMainResponseModel(): List<SchoolTrajectory> {
        val school = SchoolTrajectory("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")

        val schoolTrajectoryList = ArrayList<SchoolTrajectory>()

        schoolTrajectoryList.add(school)

        return schoolTrajectoryList
    }
}