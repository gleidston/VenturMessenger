package br.org.venturus.venturmessenger.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.org.venturus.venturmessenger.ChatActivity
import br.org.venturus.venturmessenger.databinding.FragmentMainBinding
import br.org.venturus.venturmessenger.model.Contact
import br.org.venturus.venturmessenger.repository.ChatRepository
import br.org.venturus.venturmessenger.repository.UserRepository
import br.org.venturus.venturmessenger.viewmodel.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val contactList = binding.contactList
        val adapter = ContactsAdapter {
            contact -> onContactSelected(contact)
        }
        contactList.adapter = adapter
        pageViewModel.contactsList.observe(viewLifecycleOwner, Observer {
            adapter.contacts = it
        })

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    private fun onContactSelected(contact: Contact) {
        val intent: Intent = Intent(context, ChatActivity::class.java)
        val email = UserRepository.myEmail()
        if (email != null) {
            val chatId = ChatRepository.createChatId(email, contact.email)
                intent.putExtra("chatId", chatId)
                intent.putExtra("contactEmail", contact.email)
        }
        startActivity(intent)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}