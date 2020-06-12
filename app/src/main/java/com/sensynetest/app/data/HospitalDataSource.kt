package com.example.kotlinproj.data

import androidx.lifecycle.LiveData
import com.sensynetest.app.domain.Hospital

interface HospitalDataSource {
    fun getHospitalList(): LiveData<List<Hospital>>
    fun getHospitalListBySector(sector : String): LiveData<List<Hospital>>
    suspend fun loadHospitalList()
}