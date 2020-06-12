package com.example.kotlinproj.presenter

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sensynetest.app.R
import com.sensynetest.app.framework.AppPreferenceManager


class HospitalListFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var personViewModel : HospitalViewModel
    lateinit var recyclerView : RecyclerView
    lateinit var filterFloatingActionButton: FloatingActionButton
    lateinit var hospitalAdapter : HospitalAdapter
    lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.activity_main,container,false)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view)
        progressBar = rootView.findViewById(R.id.progressBar)
        filterFloatingActionButton = rootView.findViewById(R.id.fab_filter_hospital)
        return  rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

       init();
        super.onActivityCreated(savedInstanceState)
    }

    fun init(){

        //show filter list as of now we are just supporting by NHS
        filterFloatingActionButton.setOnClickListener{
            showFilter()
        }

        hospitalAdapter = HospitalAdapter(context!!)
        with(recyclerView){
            layoutManager = LinearLayoutManager(this@HospitalListFragment.context, LinearLayoutManager.VERTICAL,false)
            adapter = hospitalAdapter
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        personViewModel = ViewModelProvider(this,
            HospitalViewModelFactory(activity!!.application)
        ).get(HospitalViewModel::class.java)
        progressBar.visibility = View.VISIBLE;
        personViewModel.loadHospitalData()

        populateHospitalListBasedOnCurrentFilter();

    }

    private fun populateHospitalListBasedOnCurrentFilter(){
        if(AppPreferenceManager.currentFilter == 0){
            populateAllHospitalList();
        }
        else{
            populateAllHospitalListByNHS();
        }
    }

    private fun populateAllHospitalListByNHS(){
        progressBar.visibility = View.VISIBLE;
        personViewModel.getHospitalDataBySector("NHS").observe(this, Observer {
            progressBar.visibility = View.GONE;
            hospitalAdapter.updateHospitalList(it)
        })
    }

    private fun populateAllHospitalList(){
        progressBar.visibility = View.VISIBLE;
        personViewModel.getHospitalData().observe(this, Observer {
            progressBar.visibility = View.GONE;
            hospitalAdapter.updateHospitalList(it)
        })
    }

    fun showFilter() {
        val builder = AlertDialog.Builder(activity)
        with(builder)
        {
            setTitle(R.string.select_filter)
            setItems(R.array.filter_list){dialog, which ->  
               AppPreferenceManager.currentFilter = which
                populateHospitalListBasedOnCurrentFilter()
            }

            show()
        }
    }
}