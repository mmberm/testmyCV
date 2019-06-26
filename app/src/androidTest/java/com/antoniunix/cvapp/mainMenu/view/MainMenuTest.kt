package com.antoniunix.cvapp.mainMenu.view

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.antoniunix.cvapp.AppCvAplication
import com.antoniunix.cvapp.di.AppModuleMock
import com.antoniunix.domain.models.CV
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.models.SchoolTrajectory
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.antoniunix.cvapp.R
import com.antoniunix.cvapp.di.DaggerAppComponentMock
import org.junit.Test



@RunWith(AndroidJUnit4::class)
class MainMenuTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainMenu::class.java, true, false)

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
    fun checkLabels() {
        onView(ViewMatchers.withId(R.id.nameLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.name)))
        onView(ViewMatchers.withId(R.id.ageLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.age )))
        onView(ViewMatchers.withId(R.id.telHomeLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.home_tel)))
        onView(ViewMatchers.withId(R.id.telCellPhoneLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.mobile_number)))
        onView(ViewMatchers.withId(R.id.professionLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.profession)))
        onView(ViewMatchers.withId(R.id.curpLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.curp)))
        onView(ViewMatchers.withId(R.id.rfcLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.rfc)))
        onView(ViewMatchers.withId(R.id.nationalityLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.nationality)))
        onView(ViewMatchers.withId(R.id.addressLabelTextView)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.address)))
    }

    @Test
    fun checkValuesOfText(){
        onView(ViewMatchers.withId(R.id.nameTextView)).check(ViewAssertions.matches(ViewMatchers.withText("mi nombre")))
        onView(ViewMatchers.withId(R.id.ageTextView)).check(ViewAssertions.matches(ViewMatchers.withText("15")))
        onView(ViewMatchers.withId(R.id.telHomeTextView)).check(ViewAssertions.matches(ViewMatchers.withText("555555")))
        onView(ViewMatchers.withId(R.id.telCellPhoneTextView)).check(ViewAssertions.matches(ViewMatchers.withText("666666")))
        onView(ViewMatchers.withId(R.id.professionTextView)).check(ViewAssertions.matches(ViewMatchers.withText("albañil")))
        onView(ViewMatchers.withId(R.id.curpTextView)).check(ViewAssertions.matches(ViewMatchers.withText("wertyuio")))
        onView(ViewMatchers.withId(R.id.rfcTextView)).check(ViewAssertions.matches(ViewMatchers.withText("rtyuio")))
        onView(ViewMatchers.withId(R.id.nationalityTextView)).check(ViewAssertions.matches(ViewMatchers.withText("china")))
        onView(ViewMatchers.withId(R.id.addressTextView)).check(ViewAssertions.matches(ViewMatchers.withText("calle siempre viva")))
    }

    @Test
    fun goToSchoolActivity(){
        onView(ViewMatchers.withId(R.id.goToSchoolButton)).perform(ViewActions.click())
    }

    @Test
    fun goToProfessionActivity(){
        onView(ViewMatchers.withId(R.id.goToProfessionalButton)).perform(ViewActions.click())
    }

}