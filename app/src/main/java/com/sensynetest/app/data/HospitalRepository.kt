package com.example.kotlinproj.data

import androidx.lifecycle.LiveData
import com.sensynetest.app.domain.Hospital

class HospitalRepository(private val hospitalDataSource : HospitalDataSource){

    fun getHospitalList(): LiveData<List<Hospital>> {
        return hospitalDataSource.getHospitalList()
    }

    fun getHospitalListBySector(sector : String): LiveData<List<Hospital>> {
        return hospitalDataSource.getHospitalListBySector(sector)
    }

    suspend fun loadHospitalList(){
        return hospitalDataSource.loadHospitalList()
    }
}