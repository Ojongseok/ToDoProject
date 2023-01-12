package com.example.todoproject.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoproject.model.TodoModel

@Database(
    entities = [TodoModel::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDAO

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        private fun buildDatabase(context: Context): TodoDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "Todo.db"
            ).build()

        fun getInstance(context: Context): TodoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}