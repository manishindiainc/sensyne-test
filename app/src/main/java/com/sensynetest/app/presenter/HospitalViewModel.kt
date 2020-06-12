package com.example.kotlinproj.presenter

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinproj.data.HospitalRepository
import com.example.kotlinproj.framework.HospitalDataSourceImpl
import com.example.kotlinproj.usecase.DisplayHospitalsUseCase
import com.sensynetest.app.domain.Hospital
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HospitalViewModel(application: Application) : AndroidViewModel(application) {
    val repo : HospitalRepository
    val displayHospitalUseCase: DisplayHospitalsUseCase
    var data : LiveData<List<Hospital>> = MutableLiveData()
    var sectorData : LiveData<List<Hospital>> = MutableLiveData()

    init {
        repo = HospitalRepository(HospitalDataSourceImpl(application))
        displayHospitalUseCase = DisplayHospitalsUseCase(repo)
    }

    fun getHospitalData() : LiveData<List<Hospital>>{
        data = repo.getHospitalList()
        return data;
    }

    fun getHospitalDataBySector(sector : String) : LiveData<List<Hospital>>{
        sectorData = repo.getHospitalListBySector(sector)
        return sectorData;
    }

    fun loadHospitalData() {
        viewModelScope.launch{
            loadData();
        }
    }

    private  suspend fun loadData() {
        return withContext(Dispatchers.Default){
            displayHospitalUseCase.execute()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}