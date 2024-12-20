package com.test.overview.data

import android.database.Observable
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.test.overview.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}


@Dao
interface UsersDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<User>>
}