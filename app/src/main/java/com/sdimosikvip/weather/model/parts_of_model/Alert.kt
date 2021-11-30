package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class Alert(
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("end")
    val end: Double,
    @field:SerializedName("event")
    val event: String,
    @field:SerializedName("sender_name")
    val senderName: String,
    @field:SerializedName("start")
    val start: Double,
    @field:SerializedName("tags")
    val tags: List<String>
)
