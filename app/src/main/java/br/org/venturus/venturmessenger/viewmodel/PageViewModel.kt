package br.org.venturus.venturmessenger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.org.venturus.venturmessenger.model.Contact
import br.org.venturus.venturmessenger.repository.UserRepository

class PageViewModel : ViewModel() {


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Lista : $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }


    private val _contactsList = MutableLiveData<ArrayList<Contact>>().apply {
        UserRepository.getMyContacts {
            value = it
        }
    }

    val contactsList: LiveData<ArrayList<Contact>> = _contactsList
}