package com.antoniunix.cvapp.professionalTrajectory.presenter

import com.antoniunix.domain.usescase.GetProfessionTrajectoryUseCase
import io.reactivex.disposables.CompositeDisposable

class ProfessionalTrajectoryPresenter(
        private val view: ProfessionalTrajectoryContract.View,
        private val getProfessionTrajectoryUseCase: GetProfessionTrajectoryUseCase
) : ProfessionalTrajectoryContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getProfessionalTrajectory() {
        compositeDisposable.add(getProfessionTrajectoryUseCase.execute()
                .subscribe({ professionalData ->
                    if (professionalData == null) {
                        view.showError("ERROR NULL")
                    } else {
                        view.showInformation(professionalData)
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