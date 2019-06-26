package com.antoniunix.cvapp.mainMenu.presenter

import com.antoniunix.domain.manager.ResourceManagerError
import com.antoniunix.domain.usescase.GetCvUseCase
import com.antoniunix.domain.usescase.GetProfileUseCase
import io.reactivex.disposables.CompositeDisposable

class MainMenuPresenter(
        private val view: MainMenuContract.View,
        private val getProfileUseCase: GetProfileUseCase,
        private val getCvUseCase: GetCvUseCase,
        private val resourceManager: ResourceManagerError
) : MainMenuContract.Presenter {

    private val compositeDisposable = CompositeDisposable()


    override fun getCv() {
        compositeDisposable.add(getCvUseCase.execute()
                .doOnSubscribe { view.showProgressBar() }
                .subscribe({ cvData ->
                    view.hideProgressBar()
                    if (cvData == null) {
                        view.showError(resourceManager.getGenericExceptionMessage())
                    } else {
                        getProfile()
                    }
                }, {
                    view.hideProgressBar()
                    if (it is GetProfileUseCase.CatalogException.TimeoutException) {
                        view.showError(resourceManager.getTimeoutExceptionMessage())
                    } else {
                        view.showError(resourceManager.getConnectionErrorMessage())
                    }
                }))
    }

    override fun getProfile() {
        compositeDisposable.add(getProfileUseCase.execute()
                .subscribe({ profileData ->
                    if (profileData == null) {
                        view.showError(resourceManager.getGenericExceptionMessage())
                    } else {
                        view.showInformation(profileData)
                    }
                }, {
                    view.showError(resourceManager.getRealmExceptionMessage())
                }))
    }

    override fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}