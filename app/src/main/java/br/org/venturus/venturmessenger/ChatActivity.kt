package br.org.venturus.venturmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.org.venturus.venturmessenger.databinding.ActivityChatBinding
import br.org.venturus.venturmessenger.repository.ChatRepository
import br.org.venturus.venturmessenger.repository.UserRepository

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

        val messages = binding.messages
        val me = UserRepository.myEmail()

        if (chatId != null) {
            ChatRepository.getMessages(chatId) {
                messages.text.clear()
                for(msg in it){
                    messages.text.append("${msg.message}\n")
                }
            }
        }

        binding.btnSend.setOnClickListener {
            val msg = binding.txtMessage.text.toString()
            ChatRepository.addMessageToChat(chatId, me!!, msg)
        }
    }
}