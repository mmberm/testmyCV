package com.antoniunix.data.repository

import android.util.Log
import com.antoniunix.data.db.RealmSource
import com.antoniunix.data.mappers.CVMapper
import com.antoniunix.data.model.response.MainresponseModel
import com.antoniunix.data.model.response.ProfileModel
import com.antoniunix.data.services.CVServices
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.repository.CVRepository
import com.antoniunix.domain.usescase.GetProfileUseCase
import io.reactivex.Single
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CVDataRepository @Inject constructor(
        val cvServices: CVServices,
        val realmDataSource: RealmSource
) : CVRepository {


    override fun getCV(): Single<CV> {
        return cvServices.getCV()
                .map {
                    insertToRealm(it)
                    CVMapper().transform(it)
                }.onErrorResumeNext {
                    Single.error(getErrorHandler(it))
                }
    }

    override fun getProfile(): Single<Profile> {
        return realmDataSource.getProfile()
                .map {
                    it
                }.onErrorResumeNext {
                    Single.error(GetProfileUseCase.CatalogException.GenericError())
                }
    }

    override fun getSchoolTrajectory(): Single<List<SchoolTrajectory>> {
        return realmDataSource.getSchoolTrajectory()
                .map {
                    it
                }.onErrorResumeNext {
                    Single.error(GetProfileUseCase.CatalogException.GenericError())
                }
    }

    override fun getProfessionalTrajectory(): Single<List<ProfessionalTrajectory>> {
        return realmDataSource.getProfessional()
                .map {
                    it
                }.onErrorResumeNext {
                    Single.error(GetProfileUseCase.CatalogException.GenericError())
                }
    }


    private fun insertToRealm(mainresponseModel: MainresponseModel) {
        val profileModel = mainresponseModel.profileModel ?: ProfileModel()
        val schoolModel = mainresponseModel.schoolTrajectoryModel
        val professionModel = mainresponseModel.professionalTrajectoryModel
        realmDataSource.saveProfile(profileModel)
        realmDataSource.saveSchoolTrajectory(schoolModel)
        realmDataSource.saveProfessionalTrajectory(professionModel)
    }


    private fun getErrorHandler(throwable: Throwable): Throwable {
        return when (throwable) {
            is SocketTimeoutException -> GetProfileUseCase.CatalogException.TimeoutException()
            else -> GetProfileUseCase.CatalogException.GenericError()
        }
    }

}