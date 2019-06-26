package com.antoniunix.cvapp.professionalTrajectory.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antoniunix.cvapp.R
import com.antoniunix.domain.models.ProfessionalTrajectory
import kotlinx.android.synthetic.main.professional_row.view.*
import javax.inject.Inject

class ProfessionalAdapter @Inject constructor(val items: List<ProfessionalTrajectory>)
    : RecyclerView.Adapter<ProfessionalAdapter.ViewHolderProfessional>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderProfessional {
        return ViewHolderProfessional(LayoutInflater.from(p0.context).inflate(R.layout.professional_row, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolderProfessional, p1: Int) {
        p0.bin(items.get(p1))
    }


    class ViewHolderProfessional(view: View) : RecyclerView.ViewHolder(view) {
        fun bin(professional: ProfessionalTrajectory) {
            itemView.companyNameTextView.text = professional.companyName
            itemView.companyTelTextView.text = professional.tel
            itemView.startDateTextView.text = professional.sartDate
            itemView.endDateTextView.text = professional.endDate
            itemView.positionTextView.text = professional.position
        }
    }

}