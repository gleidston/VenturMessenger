package br.org.venturus.venturmessenger.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.org.venturus.venturmessenger.model.Contact
import br.org.venturus.venturmessenger.repository.UserRepository

class PageViewModel : ViewModel() {

    private val _contactsList = MutableLiveData<ArrayList<Contact>>().apply {
        UserRepository.getMyContacts {
            value = it
        }
    }

    val contactsList: LiveData<ArrayList<Contact>> = _contactsList
}