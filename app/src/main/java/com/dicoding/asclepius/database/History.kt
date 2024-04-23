package com.dicoding.asclepius.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(

    @PrimaryKey(autoGenerate = false)

    var image: String = " ",

    var result: String = " ",

    var confidence: String = " "

)
