package com.example.kotlinproj.usecase

import com.example.kotlinproj.data.HospitalRepository
import com.sensynetest.app.domain.Hospital

class DisplayHospitalsUseCase(private val repository : HospitalRepository) {


    suspend fun execute(){
        return repository.loadHospitalList();
    }
}