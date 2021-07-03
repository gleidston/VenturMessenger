package br.org.venturus.venturmessenger.repository

import android.util.Log
import br.org.venturus.venturmessenger.model.Contact
import br.org.venturus.venturmessenger.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserRepository {
    private const val TAG: String = "UserRepository"
    private val db_firestore by lazy { Firebase.firestore }

    fun myEmail(): String? {
        return FirebaseAuth.getInstance().currentUser?.email
    }

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

    fun getMyContacts(onComplete: (ArrayList<Contact>) -> Unit) {
        val current = FirebaseAuth.getInstance().currentUser?.apply {
            if(this.email != null){
                val docRef = db_firestore.collection("users")
                    .document(this.email!!)
                    .collection("contacts")
                docRef.get()
                    .addOnSuccessListener { documents ->
                        if (documents != null) {
                            val contacts = ArrayList<Contact>()
                            for (document in documents) {
                                contacts.add(Contact(name = document.data.getValue("name") as String,
                                    email = document.data.getValue("email") as String))
                            }
                            onComplete(contacts)
                        } else {
                            Log.d(TAG, "No contacts found")
                            onComplete(ArrayList<Contact>())
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get contacts failed with ", exception)
                        onComplete(ArrayList<Contact>())
                    }
            } else {
                Log.e(TAG, "Current user has no email")
                onComplete(ArrayList<Contact>())
            }
        }
        Log.e(TAG, "Current user not found")
        onComplete(ArrayList<Contact>())
    }
}