package com.cemreonur.kotlinroomyoutube.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val age: Int) : Parcelable