package br.org.venturus.venturmessenger.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.org.venturus.venturmessenger.databinding.FragmentMainBinding
import br.org.venturus.venturmessenger.model.ContactsAdapter

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceHolderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val contactList: RecyclerView = binding.contactList
        val adapter: ContactsAdapter = ContactsAdapter()
        pageViewModel.contactsList.observe(viewLifecycleOwner, Observer {
            adapter.setContactList(it)
        })

        return root
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
        fun newInstance(sectionNumber: Int): PlaceHolderFragment {
            return PlaceHolderFragment().apply {
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