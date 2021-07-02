package br.org.venturus.venturmessenger.database

import br.org.venturus.venturmessenger.model.User
import br.org.venturus.venturmessenger.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class firestormDAO {
    fun insertUser(){
        //Success
        FirebaseAuth.getInstance().currentUser?.apply {
            val user = User(
                name = this.displayName ?: "NO NAME",
                email = this.email ?: "NO EMAIL",
                id = this.uid
            )
            UserRepository.addUser(user, {

            }) {

            }
        }
    }
}