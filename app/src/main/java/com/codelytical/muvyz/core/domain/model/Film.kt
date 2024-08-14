package com.codelytical.muvyz.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val type: String,
    val image: String,
    val category: String,
    val name: String,
    val rating: Float,
    val releaseDate: String,
    val overview: String
) : Parcelable
