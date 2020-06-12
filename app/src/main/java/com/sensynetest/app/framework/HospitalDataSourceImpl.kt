package com.example.kotlinproj.framework

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kotlinproj.data.HospitalDataSource
import com.sensynetest.app.domain.Hospital
import com.sensynetest.app.framework.Constants
import com.sensynetest.app.framework.HospitalDatabase
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.*


class HospitalDataSourceImpl(val application: Application) : HospitalDataSource {
    private val hospitalDB : HospitalDatabase by lazy{
        HospitalDatabase.getDatabase(application)
    }
    companion object{
        const val TAG : String = "HospitalDataSourceImpl"
    }

    override fun getHospitalList(): LiveData<List<Hospital>> {
        return  hospitalDB.hospitalDao().getAll()
    }

    override fun getHospitalListBySector(sector : String): LiveData<List<Hospital>> {
        return  hospitalDB.hospitalDao().getHospitalBySector(sector)
    }

    override suspend fun loadHospitalList() {
        //check if data exist in DB or not
        if(hospitalDB.hospitalDao().getCount() > 0)
            return;

        //check file is downloaded from server or not, if not try to download it.
        val downloaded = downloadIfFileNotExist();
        val iStream : InputStream = if (downloaded) getFileStreamFromInternalDir() else getFileStreamFromIAsset()
        val itemList: List<Hospital> = readHospitalListFromFile(iStream)

        hospitalDB.hospitalDao().insertAll(itemList)
    }

    private suspend fun downloadIfFileNotExist() : Boolean{
        val file = File(application.filesDir, Constants.FILE_NAME)
        if(file.exists())
            return true

        val response : Response<ResponseBody> = ApiClient.getClient.download()
        if(response.isSuccessful){
            try {
                var bytes: ByteArray? = response.body()?.bytes()
                if (bytes != null) {
                    //there is some invalid char which is acting as delimiter
                    //for each line in CSV, we will change this value to tab
                    // tab will act as delimiter for both local and server
                    //csv file
                    for (i in bytes.indices) {
                        if(bytes[i].toInt() == Constants.DELIMITER_FROM_SERVER)
                            bytes[i] = '\t'.toByte()
                    }
                    val outputStream =
                        FileOutputStream(file)
                    outputStream.write(bytes)
                    outputStream.close()
                    return true
                }
            }
            catch (e: Exception){
                e.printStackTrace()
                return false
            }
        }
        return false
    }

    private fun getFileStreamFromInternalDir() : InputStream {
        return FileInputStream(File(application.filesDir, Constants.FILE_NAME))
    }

    private fun getFileStreamFromIAsset() : InputStream{
        return application.assets.open(Constants.FILE_NAME)
    }

    private fun readHospitalListFromFile(iStream : InputStream):List<Hospital>{
        var output = mutableListOf<Hospital>();
        val reader  = BufferedReader(InputStreamReader(iStream))

        var line: String? = null;
        var isFirstLine = true
        while ({ line = reader.readLine(); line }() != null) {
            if(line != null) {
                if(!isFirstLine) {
                    var list = line?.split("\\t".toRegex())
                    if (list != null) {
                        val hospital = getHosPitalFromList(list)
                        if (hospital != null)
                            output.add(hospital)
                    }
                }
                isFirstLine = false
            }
        }

        return output;
    }

    private fun getHosPitalFromList(list:List<String>?) : Hospital?{
        //there 22 columns, if the list contains less just avoid it
        if(list == null || list.size < 22)
            return null

        return Hospital(list[0],list[1],list[2],list[3],
            list[4],list[5],list[6],list[7],
            list[8],list[9],list[10],list[11],
            list[12],list[13],list[14],list[15],
            list[16],list[17],list[18],list[19],list[20],list[21])
    }
}