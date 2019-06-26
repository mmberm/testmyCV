package com.antoniunix.cvapp.mainMenu.presenter

import com.antoniunix.domain.models.Profile

interface MainMenuContract {
    interface Presenter {
        fun getCv()
        fun getProfile()
        fun dispose()

    }

    interface View {
        fun initView()
        fun showInformation(profile: Profile)
        fun showError(errorMessage: String)
        fun goToSchoolTrajectory()
        fun goToProfessionalTrajectory()
        fun showProgressBar()
        fun hideProgressBar()
    }
}