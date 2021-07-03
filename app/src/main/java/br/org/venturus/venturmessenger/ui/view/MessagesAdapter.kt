package br.org.venturus.venturmessenger.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.org.venturus.venturmessenger.R
import br.org.venturus.venturmessenger.model.Message

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var messages: ArrayList<Message> = ArrayList<Message>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val msg = view.findViewById<TextView>(R.id.msg)

        fun setMsg(message: Message) {
            msg.text = message.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesAdapter.ViewHolder, position: Int) {
        holder.setMsg(messages[position])
    }

    override fun getItemCount(): Int {
        return messages.size
    }


}