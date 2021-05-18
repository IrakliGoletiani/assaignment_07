package com.example.homework06

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthYear: Int,
    val gender: String
) : Parcelable