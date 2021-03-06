package br.org.venturus.venturmessenger.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.org.venturus.venturmessenger.R
import br.org.venturus.venturmessenger.model.Contact

class ContactsAdapter(val onContactSelected: (contact: Contact) -> Unit) : RecyclerView.Adapter<ContactsAdapter.ViewHolder> (){

    var contacts: ArrayList<Contact> = ArrayList<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val contactName: TextView = view.findViewById(R.id.contactName)
        val contactDetails: TextView = view.findViewById(R.id.contactDetails)
        val send: ImageView = view.findViewById(R.id.send)

        fun setUser(contact: Contact){
            contactName.text = contact.name
            contactDetails.text = contact.email

        }
    }

    fun setContactList(contacts: ArrayList<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_item, viewGroup,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUser(contacts[position])
        holder.send.setOnClickListener {
            onContactSelected(contacts[position])
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}