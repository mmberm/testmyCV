package com.antoniunix.cvapp.schoolTrajectory.view

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.antoniunix.cvapp.AppCvAplication
import com.antoniunix.cvapp.R
import com.antoniunix.cvapp.customMatchs.CustomMatch
import com.antoniunix.cvapp.di.AppModuleMock
import com.antoniunix.cvapp.di.DaggerAppComponentMock
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SchoolTrajectoryActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SchoolTrajectoryActivity::class.java, true, false)

    @Before
    fun setupMainActivity() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as AppCvAplication

        val testComponent = DaggerAppComponentMock.builder()
                .appModuleMock(AppModuleMock(fillMainResponseModel()))
                .build()
        app.appComponent = testComponent
        activityRule.launchActivity(Intent())
    }

    private fun fillMainResponseModel(): CV {
        var cv = CV()
        val profile = Profile("mi nombre", "555555", "666666",
                "albañil", "calle siempre viva", "15", "wertyuio",
                "rtyuio", "china", "fghjuytyuy")
        val school = SchoolTrajectory("IMSS", "kinder",
                "01 diciembre 2009", "2 diciembre 2009", "cocinero")
        val professionalTrajectory = ProfessionalTrajectory("globant",
                "456789", "3 enero 2008", "4 enero 2019", "plomero")

        val schoolTrajectoryList = ArrayList<SchoolTrajectory>()
        val professionalTrajectoryList = ArrayList<ProfessionalTrajectory>()

        schoolTrajectoryList.add(school)
        professionalTrajectoryList.add(professionalTrajectory)

        cv.profile = profile
        cv.professionalTrajectory = professionalTrajectoryList
        cv.schoolTrajectory = schoolTrajectoryList
        return cv
    }

    @Test
    fun checkContainSchoolList() {
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.withItemCount(1)))
    }

    @Test
    fun checkTitleItemOne() {
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("IMSS")))))
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("kinder")))))
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("01 diciembre 2009")))))
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("2 diciembre 2009")))))
        Espresso.onView(ViewMatchers.withId(R.id.school_recycler))
                .check(ViewAssertions.matches(CustomMatch.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("cocinero")))))
    }


}