package com.example.kotlinproj.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sensynetest.app.R
import com.sensynetest.app.domain.Hospital

class HospitalAdapter(private val context : Context) : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    private var hospitalList : ArrayList<Hospital> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        var view  = LayoutInflater.from(context).inflate(R.layout.hospital_list_item, parent, false)


        return HospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bindData(context, hospitalList[position])
    }

    fun updateHospitalList(list : List<Hospital>){
        hospitalList.clear();
        hospitalList.addAll(list);
        notifyDataSetChanged()
    }



    inner class HospitalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvOrganisationName : TextView
        init {
            tvOrganisationName = itemView.findViewById<TextView>(R.id.organisationDetail)
            itemView.setOnClickListener {
                handleOnClick(it)
            }
        }
       fun bindData(context : Context, hospital: Hospital){
           var builder = StringBuilder()
           builder
               .append("Name : ").append(hospital.organisationName).append("\n")
               .append("Type : ").append(hospital.organisationType).append("\n")
               .append("Sector : ").append(hospital.sector).append("\n")
               .append("Id : ").append(hospital.organisationID)
           tvOrganisationName.text = builder.toString()
       }

        private fun handleOnClick(view : View){
            var fragment = HospitalDetailFragment()
            fragment.setHospital(hospitalList[adapterPosition])
            (view.getContext() as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}