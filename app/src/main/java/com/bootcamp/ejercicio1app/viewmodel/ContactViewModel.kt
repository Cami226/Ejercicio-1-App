package com.bootcamp.ejercicio1app.viewmodel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.ejercicio1app.model.Contact
import com.bootcamp.ejercicio1app.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {


    var contactNameState by mutableStateOf("")
    var contactPhoneState by mutableStateOf("")
    var contactMailState by mutableStateOf("")
    var contactImageState by mutableStateOf("")
    var contactBirthdateState by mutableStateOf("")


    fun onContacNameChanged(newString: String) {
        contactNameState = newString
    }

    fun onContacPhoneChanged(newString: String) {
        contactPhoneState = newString
    }

    fun onContacMailChanged(newString: String) {
        contactMailState = newString
    }

    fun onContacImageChanged(newString: String) {
        contactImageState = newString
    }

    fun onContacBirthdateChanged(newString: String) {
        contactBirthdateState = newString
    }



    val contacts: Flow<List<Contact>> = repository.getAllContacts()

    fun getAnContactById(id: Long): Flow<Contact>{
        return repository.getAnContactbyId(id)
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }
}