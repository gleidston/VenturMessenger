package br.org.venturus.venturmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.org.venturus.venturmessenger.databinding.ActivityChatBinding
import br.org.venturus.venturmessenger.repository.ChatRepository
import br.org.venturus.venturmessenger.repository.UserRepository
import br.org.venturus.venturmessenger.ui.view.MessagesAdapter

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatId = intent.getStringExtra("chatId")
        val contactEmail = intent.getStringExtra("contactEmail")
        if (chatId == null || contactEmail == null) {
               finish()
                return
        }

        val me = UserRepository.myEmail()

        val recycler = binding.recyclerView
        val adapter = MessagesAdapter()
        recycler.adapter = adapter

        if (chatId != null) {
            ChatRepository.getMessages(chatId) {
                adapter.messages = it
            }
        }

        binding.btnSend.setOnClickListener {
            val msg = binding.txtMessage.text.toString()
            ChatRepository.addMessageToChat(chatId, me!!, msg)
            binding.txtMessage.text.clear()
        }
    }
}