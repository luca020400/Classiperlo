package com.luca020400.classiperlo.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.luca020400.classiperlo.R

class ContactFragment : Fragment() {

    private val contactViewModel by viewModels<ContactViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        val textView: TextView = root.findViewById(R.id.text_contact)
        textView.setOnClickListener {
            // Create an instance of the dialog fragment and show it
            val dialog = ContactDialogFragment {
                contactViewModel.option.value = ContactViewModel.Options.fromInt(it)
            }
            dialog.show(childFragmentManager, "ContactDialogFragment")
        }
        contactViewModel.option.observe(viewLifecycleOwner, Observer {
            textView.text = resources.getStringArray(R.array.contact_options)[it.ordinal]
            // Create the secondary fragment
        })
        return root
    }
}