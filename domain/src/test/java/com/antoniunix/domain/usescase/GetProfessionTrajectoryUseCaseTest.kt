package com.antoniunix.domain.usescase

import com.antoniunix.domain.executor.ImmediateThreadExecutor
import com.antoniunix.domain.executor.PostExecutionThread
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.repository.CVRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetProfessionTrajectoryUseCaseTest{

    @Mock
    private lateinit var cVRepository: CVRepository
    @Mock
    private lateinit var postExecutor: PostExecutionThread

    private lateinit var getProfessionUseCase: GetProfessionTrajectoryUseCase


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        getProfessionUseCase = GetProfessionTrajectoryUseCase(cVRepository, ImmediateThreadExecutor(), postExecutor)
    }

    @Test
    fun getProfessionUseCase() {
        val cv = fillProfessionTrayectoryModel()

        Mockito.`when`(cVRepository.getProfessionalTrajectory()).thenReturn(Single.just(cv))

        val receivedResponse = cVRepository.getProfessionalTrajectory().test().assertComplete().values()[0]

        val professionalTrajectory = receivedResponse.get(0)
        Assert.assertEquals(professionalTrajectory.companyName, cv.get(0).companyName)
        Assert.assertEquals(professionalTrajectory.tel, cv.get(0).tel)
        Assert.assertEquals(professionalTrajectory.sartDate, cv.get(0).sartDate)
        Assert.assertEquals(professionalTrajectory.endDate, cv.get(0).endDate)
        Assert.assertEquals(professionalTrajectory.position, cv.get(0).position)
    }

    private fun fillProfessionTrayectoryModel(): List<ProfessionalTrajectory> {
        val professionalTrajectory = ProfessionalTrajectory("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val professionalTrajectoryList = ArrayList<ProfessionalTrajectory>()

        professionalTrajectoryList.add(professionalTrajectory)
        return professionalTrajectoryList
    }
}