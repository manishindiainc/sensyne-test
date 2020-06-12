package com.sensynetest.app

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sensynetest.app.domain.Hospital
import com.sensynetest.app.framework.HospitalDao
import com.sensynetest.app.framework.HospitalDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class HospitalDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val hospital = Hospital("123","NDA07","Hospital",
        "Hospital", "Independent sector","visible",
        "true", "Walton community","address1",
        "address2", "address3","city", "county",
        "postcode","3.37", "4.86","odscode",
        "parent name", "1334343", "abc@def.com","","76766")

    val hospitalNHS = Hospital("34334","HD0A5","Hospital",
        "Hospital", "NHS sector","visible",
        "true", "Walton community","address1",
        "address2", "address3","city", "county",
        "postcode","3.37", "4.86","odscode",
        "parent name", "1334s343", "ab4c@def.com","","7698766")

    private lateinit var hospitalDao: HospitalDao
    private lateinit var db: HospitalDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, HospitalDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        hospitalDao = db.hospitalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndCount() {
        var list = mutableListOf<Hospital>(hospital, hospitalNHS)
        hospitalDao.insertAll(list)
        val count = hospitalDao.getCount()
        assertEquals(count, 2)
    }

    @Test
    @Throws(Exception::class)
    fun getAllHospitalList() {
        var list = mutableListOf<Hospital>(hospital, hospitalNHS)
        hospitalDao.insertAll(list)
        val allWords = hospitalDao.getAll().waitForValue()
        assertEquals(allWords[0].organisationID, "123")
        assertEquals(allWords[1].organisationID, "34334")
    }

    @Test
    @Throws(Exception::class)
    fun getAllNHSHospitals() {
        var list = mutableListOf<Hospital>(hospital, hospitalNHS)
        hospitalDao.insertAll(list)
        val allWords = hospitalDao.getHospitalBySector("NHS").waitForValue()
        assertEquals(allWords.size, 1)
        assertEquals(allWords[0].organisationID, "34334")
    }
}