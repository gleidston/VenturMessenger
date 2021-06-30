package br.org.venturus.venturmessenger.repository

import br.org.venturus.venturmessenger.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserRepository {
    private val db_firestore by lazy { Firebase.firestore }

    fun addUser(user: User, onSuccess: () -> Unit, onFail: (error: String) -> Unit) {
        //add a new document with a generated ID
        db_firestore.collection("users")
            .document(user.email)
            .set(user)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFail(e.localizedMessage)
            }
    }
}