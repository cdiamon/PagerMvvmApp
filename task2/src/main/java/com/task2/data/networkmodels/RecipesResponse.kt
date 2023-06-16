package com.task2.data.networkmodels

import kotlinx.serialization.*
import java.util.*

/**
 * This is the response model for the endpoint
 * https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json{
 */
@Serializable
data class RecipesResponse(
    @SerialName("calories") val calories: String,
    @SerialName("carbos") val carbos: String,
    @SerialName("description") val description: String,
    @SerialName("difficulty") val difficulty: Int,
    @SerialName("fats") val fats: String,
    @SerialName("headline") val headline: String,
    @SerialName("id") val id: String,
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("proteins") val proteins: String,
    @SerialName("thumb") val thumb: String,
    @SerialName("time") val time: String
)
