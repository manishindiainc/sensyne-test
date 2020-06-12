package com.example.kotlinproj.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sensynetest.app.R
import com.sensynetest.app.domain.Hospital

class HospitalDetailFragment : Fragment() {

    private lateinit var rootView: View
    private var hospital: Hospital? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.activity_detail_fragment,container,false)
        return  rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

       init();
        super.onActivityCreated(savedInstanceState)
    }

    fun init(){
        var textView = rootView.findViewById<TextView>(R.id.detail_view)
        textView.text = hospital?.toString() ?: ""
    }

    fun setHospital(hospital : Hospital){
        this.hospital = hospital;
    }

}