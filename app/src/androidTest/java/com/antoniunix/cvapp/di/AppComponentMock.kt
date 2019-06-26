package com.antoniunix.cvapp.di

import com.antoniunix.cvapp.mainMenu.view.MainMenu
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleMock::class, DataModuleMock::class])
interface AppComponentMock: AppComponent {
    override fun inject(mainMenu: MainMenu)
}