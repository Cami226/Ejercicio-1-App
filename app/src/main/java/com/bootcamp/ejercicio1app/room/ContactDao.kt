package com.bootcamp.ejercicio1app.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bootcamp.ejercicio1app.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    //CRUD
    @Insert
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getAnContactById(id: Long): Flow<Contact>

}