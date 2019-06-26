package com.antoniunix.cvapp.schoolTrajectory.presenter

import com.antoniunix.domain.usescase.GetSchoolTrajectoryUseCase
import io.reactivex.disposables.CompositeDisposable

class SchoolTrajectoryPresenter(
        private val view:SchoolTrajectoryContract.View,
        private val getSchoolTrajectoryUseCase: GetSchoolTrajectoryUseCase
):SchoolTrajectoryContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getSchoolTrajectory() {
        compositeDisposable.add(getSchoolTrajectoryUseCase.execute()
                .subscribe({ schoolData ->
                    if (schoolData == null) {
                        view.showError("ERROR NULL")
                    } else {
                        view.showInformation(schoolData)
                    }
                }, {
                    view.showError("ERROR REALM")
                }))
    }

    override fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}