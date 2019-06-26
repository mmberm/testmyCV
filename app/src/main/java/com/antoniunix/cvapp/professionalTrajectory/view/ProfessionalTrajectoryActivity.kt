package com.antoniunix.cvapp.professionalTrajectory.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.antoniunix.cvapp.AppCvAplication
import com.antoniunix.cvapp.R
import com.antoniunix.cvapp.professionalTrajectory.presenter.ProfessionalTrajectoryContract
import com.antoniunix.cvapp.professionalTrajectory.presenter.ProfessionalTrajectoryPresenter
import com.antoniunix.domain.models.ProfessionalTrajectory
import com.antoniunix.domain.usescase.GetProfessionTrajectoryUseCase
import kotlinx.android.synthetic.main.activity_professional_trajecytory.*
import javax.inject.Inject

class ProfessionalTrajectoryActivity : AppCompatActivity(), ProfessionalTrajectoryContract.View {

    private var professionalAdapter: ProfessionalAdapter? = null

    @Inject
    lateinit var getProfessionTrajectoryUseCase: GetProfessionTrajectoryUseCase

    private val professionPresenter by lazy {
        ProfessionalTrajectoryPresenter(this,
                getProfessionTrajectoryUseCase)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_trajecytory)
        (application as AppCvAplication).appComponent.inject(this)
        professionPresenter.getProfessionalTrajectory()
        initView()
    }

    override fun initView() {
        title=getString(R.string.professional_trajectory)
    }

    override fun showInformation(professions: List<ProfessionalTrajectory>) {
        professionalAdapter = ProfessionalAdapter(professions)
        professionalRecyclerView.apply {
            adapter = professionalAdapter
            layoutManager = LinearLayoutManager(this@ProfessionalTrajectoryActivity)
        }

    }

    override fun showError(errorMessage: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        professionPresenter.dispose()
    }
}