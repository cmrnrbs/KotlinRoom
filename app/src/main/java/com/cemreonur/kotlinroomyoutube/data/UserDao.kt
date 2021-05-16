package com.cemreonur.kotlinroomyoutube.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM users ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}