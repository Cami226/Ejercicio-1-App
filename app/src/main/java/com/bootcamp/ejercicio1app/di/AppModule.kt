package com.bootcamp.ejercicio1app.di


import android.content.Context
import androidx.room.Room
import com.bootcamp.ejercicio1app.room.ContactDao
import com.bootcamp.ejercicio1app.room.ContactDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContactDao(contactDatabase: ContactDataBase): ContactDao {
        return contactDatabase.contactDao()
    }

    @Suppress("UNREACHABLE_CODE")
    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context): ContactDataBase {
        return Room.databaseBuilder(
            context,
            ContactDataBase::class.java,
            "ContactDB"
        )
            .fallbackToDestructiveMigration()
            .build()

    }


}