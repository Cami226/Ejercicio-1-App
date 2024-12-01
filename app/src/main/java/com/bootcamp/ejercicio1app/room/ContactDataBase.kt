package com.bootcamp.ejercicio1app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context
import androidx.room.TypeConverters
import com.bootcamp.ejercicio1app.model.Contact


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
