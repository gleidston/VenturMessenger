package br.org.venturus.venturmessenger.repository

import android.util.Log
import br.org.venturus.venturmessenger.model.Contact
import br.org.venturus.venturmessenger.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

object ChatRepository {
    private const val TAG: String = "ChatRepository"
    private val db_firestore by lazy { Firebase.firestore }

    fun getMessages(chatId: String, onComplete: (ArrayList<Message>) -> Unit) {
        val docRef = ChatRepository.db_firestore.collection("chats")
            .document(chatId)
            .collection("messages").addSnapshotListener { snapshot, e ->
                if (e != null) {
                    onComplete(ArrayList<Message>())
                    return@addSnapshotListener
                }

                val messages = ArrayList<Message>()
                if (snapshot != null) {
                    for (msg in snapshot) {
                        val m = msg.toObject<Message>()
                        messages.add(m)
                    }
                    messages.sortBy { it.time }
                    onComplete(messages)
                } else {
                    Log.d(TAG, "snapshot is null")
                }
            }
    }

    fun createChatId(email: String, contactEmail: String): String {
        val id = if(email.compareTo(contactEmail) > 0) "$email-${contactEmail}" else "${contactEmail}-$email"
        return id
    }

    fun addMessageToChat(chatId: String, from: String, message: String){
        val data = hashMapOf(
            "from" to from,
            "message" to message,
            "time" to Date().time
        )
        ChatRepository.db_firestore.collection("chats").document(chatId)
            .collection("messages").document().set(data)
    }
}
