package com.antoniunix.cvapp.schoolTrajectory.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.antoniunix.cvapp.AppCvAplication
import com.antoniunix.cvapp.R
import com.antoniunix.cvapp.schoolTrajectory.presenter.SchoolTrajectoryContract
import com.antoniunix.cvapp.schoolTrajectory.presenter.SchoolTrajectoryPresenter
import com.antoniunix.domain.models.SchoolTrajectory
import com.antoniunix.domain.usescase.GetProfileUseCase
import com.antoniunix.domain.usescase.GetSchoolTrajectoryUseCase
import kotlinx.android.synthetic.main.activity_school_trajectory.*
import javax.inject.Inject

class SchoolTrajectoryActivity : AppCompatActivity(), SchoolTrajectoryContract.View {

    private var schoolAdapter: SchoolAdapter? = null

    @Inject
    lateinit var getSchoolTrajectoryUseCase: GetSchoolTrajectoryUseCase

    private val schoolPresenter by lazy {
        SchoolTrajectoryPresenter(this,
                getSchoolTrajectoryUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_trajectory)
        (application as AppCvAplication).appComponent.inject(this)
        schoolPresenter.getSchoolTrajectory()
        initView()
    }

    override fun initView() {
        title=getString(R.string.school_trajectory)
    }

    override fun showInformation(schools: List<SchoolTrajectory>) {
        schoolAdapter = SchoolAdapter(schools)

        school_recycler.apply {
            adapter=schoolAdapter
            layoutManager=LinearLayoutManager(this@SchoolTrajectoryActivity)
        }
    }

    override fun showError(errorMessage: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        schoolPresenter.dispose()

    }
}