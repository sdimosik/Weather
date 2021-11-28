package com.sdimosikvip.weather.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sdimosikvip.weather.model.parts_of_model.LocalNames

@Entity(tableName = WeatherCoordinatesItem.TABLE_NAME)
data class WeatherCoordinatesItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: String
) {
    @field:SerializedName("unique_id")
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0;

    companion object {
        const val TABLE_NAME = "coordinate_table"
    }
}
