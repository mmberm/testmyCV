package com.antoniunix.cvapp.schoolTrajectory.presenter

import com.antoniunix.domain.models.SchoolTrajectory


interface SchoolTrajectoryContract {
    interface Presenter {
        fun getSchoolTrajectory()
        fun dispose()
    }

    interface View {
        fun initView()
        fun showInformation(schols: List<SchoolTrajectory>)
        fun showError(errorMessage: String)
    }
}