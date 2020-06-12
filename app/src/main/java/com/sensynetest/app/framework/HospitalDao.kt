package com.sensynetest.app.framework

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sensynetest.app.domain.Hospital

@Dao
interface HospitalDao {
    @Query("SELECT * FROM hospital_table")
    fun getAll(): LiveData<List<Hospital>>

    @Query("SELECT * FROM hospital_table where sector like '%' || :sectorName || '%'")
    fun getHospitalBySector(sectorName:String): LiveData<List<Hospital>>

    @Query("SELECT COUNT(*)  FROM hospital_table")
    fun getCount(): Int

    @Insert
    fun insertAll(hospitals: List<Hospital>)

}