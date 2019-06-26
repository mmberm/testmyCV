package com.antoniunix.data.db

import com.antoniunix.data.mappers.*
import com.antoniunix.data.model.response.*
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.usescase.GetProfileUseCase
import io.reactivex.Single

class RealmDataSource(private val databaseProvider: DatabaseProvider) : RealmSource {

    override fun saveProfile(profileModel: ProfileModel) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val profileRealm: ProfileRealm = ProfileToRealmMapper().transform(profileModel)
                it.deleteAll()
                it.copyToRealmOrUpdate(profileRealm)
            }
        }
    }

    override fun saveSchoolTrajectory(schoolTrajectoryModel: List<SchoolTrajectoryModel>) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val schoolRealm: List<SchoolTrajectoryRealm> = SchoolToRealmMapper().transform(schoolTrajectoryModel)
                it.copyToRealmOrUpdate(schoolRealm)
            }
        }
    }

    override fun saveProfessionalTrajectory(professionalTrajectoryModel: List<ProfessionalTrajectoryModel>) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val professionalRealm: List<ProfessionalTrajectoryRealm> = ProfessionalToRealmMapper().transform(professionalTrajectoryModel)
                it.copyToRealmOrUpdate(professionalRealm)
            }
        }
    }

    override fun getProfile(): Single<Profile> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(ProfileRealm::class.java).findFirst()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    ProfileMapper().transform(realmResults)
                }
            }
        }
    }

    override fun getSchoolTrajectory(): Single<List<SchoolTrajectory>> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(SchoolTrajectoryRealm::class.java).findAll()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    SchoolMapper().transform(realmResults)
                }
            }
        }
    }

    override fun getProfessional(): Single<List<ProfessionalTrajectory>> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(ProfessionalTrajectoryRealm::class.java).findAll()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    ProfessionalMapper().transform(realmResults)
                }
            }
        }
    }

}