package com.antoniunix.cvapp.schoolTrajectory.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antoniunix.cvapp.R
import com.antoniunix.domain.models.SchoolTrajectory
import kotlinx.android.synthetic.main.school_row.view.*
import javax.inject.Inject

class SchoolAdapter @Inject constructor(
        val items: List<SchoolTrajectory>)
    : RecyclerView.Adapter<SchoolAdapter.ViewHolderProfessional>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderProfessional {
        return ViewHolderProfessional(LayoutInflater.from(p0.context).inflate(R.layout.school_row, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolderProfessional, p1: Int) {
        p0.bin(items.get(p1))
    }


    class ViewHolderProfessional(view: View) : RecyclerView.ViewHolder(view) {
        fun bin(school: SchoolTrajectory) {
            itemView.institutionNameTextView.text = school.institutionName
            itemView.academyLevelTextView.text = school.academyLevel
            itemView.startDateTextView.text = school.initDate
            itemView.endDateTextView.text = school.finishDate
            itemView.titleObtainTextView.text = school.titleObtain
        }
    }
}