package com.antoniunix.cvapp.mainMenu.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.antoniunix.cvapp.AppCvAplication
import com.antoniunix.cvapp.R
import com.antoniunix.cvapp.mainMenu.presenter.LoadImagePicasso
import com.antoniunix.cvapp.mainMenu.presenter.MainMenuContract
import com.antoniunix.cvapp.mainMenu.presenter.MainMenuPresenter
import com.antoniunix.cvapp.professionalTrajectory.view.ProfessionalTrajectoryActivity
import com.antoniunix.cvapp.schoolTrajectory.view.SchoolTrajectoryActivity
import com.antoniunix.domain.manager.ResourceManagerError
import com.antoniunix.domain.models.Profile
import com.antoniunix.domain.usescase.GetCvUseCase
import com.antoniunix.domain.usescase.GetProfileUseCase
import kotlinx.android.synthetic.main.activity_main_menu.*
import javax.inject.Inject

class MainMenu : AppCompatActivity(), MainMenuContract.View, View.OnClickListener {

    private val imageProfile = LoadImagePicasso()

    @Inject
    lateinit var getProfileUseCase: GetProfileUseCase
    @Inject
    lateinit var getCvUseCase: GetCvUseCase
    @Inject
    lateinit var resourceManager: ResourceManagerError

    private val mainMenuPresenter by lazy {
        MainMenuPresenter(this,
                getProfileUseCase,
                getCvUseCase,
                resourceManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        (application as AppCvAplication).appComponent.inject(this)
        initView()
        mainMenuPresenter.getCv()
    }

    override fun initView() {
        title = getString(R.string.curriculum_vitae)
        goToSchoolButton.setOnClickListener(this)
        goToProfessionalButton.setOnClickListener(this)
    }

    override fun showInformation(profile: Profile) {
        imageProfile.loadImageProfile(profileImageView, profile.photo)
        nameTextView.text = profile.name
        ageTextView.text = profile.age
        telHomeTextView.text = profile.telHome
        telCellPhoneTextView.text = profile.telCellPhone
        professionTextView.text = profile.profession
        curpTextView.text = profile.curp
        rfcTextView.text = profile.rfc
        nationalityTextView.text = profile.nationality
        addressTextView.text = profile.address
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.goToSchoolButton -> goToSchoolTrajectory()
            R.id.goToProfessionalButton -> goToProfessionalTrajectory()
        }
    }

    override fun goToSchoolTrajectory() {
        startActivity(Intent(this, SchoolTrajectoryActivity::class.java))

    }

    override fun goToProfessionalTrajectory() {
        startActivity(Intent(this, ProfessionalTrajectoryActivity::class.java))
    }

    override fun showProgressBar() {
        goToSchoolButton?.visibility = View.GONE
        goToProfessionalButton?.visibility = View.GONE
        lottieLoading?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {

        lottieLoading?.visibility = View.INVISIBLE
        goToSchoolButton?.visibility = View.VISIBLE
        goToProfessionalButton?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mainMenuPresenter.dispose()
    }
}