package com.bootcamp.ejercicio1app.repository


import com.bootcamp.ejercicio1app.model.Contact
import com.bootcamp.ejercicio1app.room.ContactDao
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {

    fun getAllContacts() = contactDao.getAllContacts()

    fun getAnContactbyId(id: Long) = contactDao.getAnContactById(id)

    suspend fun addContact(contact: Contact) {
        contactDao.addContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }
}