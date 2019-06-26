package com.antoniunix.cvapp.di

import com.antoniunix.cvapp.mainMenu.view.MainMenu
import com.antoniunix.cvapp.professionalTrajectory.view.ProfessionalTrajectoryActivity
import com.antoniunix.cvapp.schoolTrajectory.view.SchoolTrajectoryActivity
import com.antoniunix.data.network.Networkmodule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, Networkmodule::class])
interface AppComponent {
    fun inject(mainMenu: MainMenu)
    fun inject(professionalTrajectoryActivity: ProfessionalTrajectoryActivity)
    fun inject(schoolTrajectoryActivity: SchoolTrajectoryActivity)
}