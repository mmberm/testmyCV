package com.antoniunix.cvapp.professionalTrajectory.presenter

import com.antoniunix.domain.models.ProfessionalTrajectory

interface ProfessionalTrajectoryContract {
    interface Presenter {
        fun getProfessionalTrajectory()
        fun dispose()
    }

    interface View {
        fun initView()
        fun showInformation(professions: List<ProfessionalTrajectory>)
        fun showError(errorMessage: String)
    }
}