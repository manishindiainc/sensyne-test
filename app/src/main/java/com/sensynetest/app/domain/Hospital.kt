package com.sensynetest.app.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital_table")
data class Hospital (
    @PrimaryKey @NonNull @ColumnInfo (name = "org_id") val organisationID : String,
    @ColumnInfo(name = "org_code") val organisationCode : String,
    @ColumnInfo(name = "org_type") val organisationType : String,
    @ColumnInfo(name = "sub_type") val subType : String,
    @ColumnInfo(name = "sector") val sector : String,
    @ColumnInfo(name = "org_status") val organisationStatus : String,
    @ColumnInfo(name = "is_managed") val isPimsManaged : String,
    @ColumnInfo(name = "org_name") val organisationName : String,
    @ColumnInfo(name = "address1") val address1 : String,
    @ColumnInfo(name = "address2") val address2 : String,
    @ColumnInfo(name = "address3") val address3 : String,
    @ColumnInfo(name = "city") val city : String,
    @ColumnInfo(name = "county") val county : String,
    @ColumnInfo(name = "postcode") val postcode : String,
    @ColumnInfo(name = "latitude") val latitude : String,
    @ColumnInfo(name = "longitude") val longitude : String,
    @ColumnInfo(name = "parent_ods_code") val parentODSCode : String,
    @ColumnInfo(name = "parent_name") val parentName : String,
    @ColumnInfo(name = "phone") val phone : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "website") val website : String,
    @ColumnInfo(name = "fax") val fax : String
){
    override fun toString(): String {
        return StringBuilder()
            .append("Org Name : ").append(organisationName).append("\n\n")
            .append("Org Type : ").append(organisationType).append("\n\n")
            .append("SubType : ").append(subType).append("\n\n")
            .append("sector : ").append(sector).append("\n\n")
            .append("Org Id : ").append(organisationID).append("\n\n")
            .append("Org Code : ").append(organisationCode).append("\n\n")
            .append("Org Status : ").append(organisationStatus).append("\n\n")
            .append("Pims Managed : ").append(isPimsManaged).append("\n\n")
            .append("Address1 : ").append(address1).append("\n\n")
            .append("Address2 : ").append(address2).append("\n\n")
            .append("Address3 : ").append(address3).append("\n\n")
            .append("City : ").append(city).append("\n\n")
            .append("County : ").append(county).append("\n\n")
            .append("Postcode : ").append(postcode).append("\n\n")
            .append("Latitude : ").append(latitude).append("\n\n")
            .append("Longitude : ").append(longitude).append("\n\n")
            .append("Parent ODS Code : ").append(parentODSCode).append("\n\n")
            .append("Parent Name : ").append(parentName).append("\n\n")
            .append("Phone : ").append(phone).append("\n\n")
            .append("Email : ").append(email).append("\n\n")
            .append("Website : ").append(website).append("\n\n")
            .append("Fax : ").append(fax).append("\n\n")
            .toString()

    }
}