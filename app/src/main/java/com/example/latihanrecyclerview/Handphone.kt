package com.example.latihanrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Handphone(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
