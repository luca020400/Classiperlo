package com.luca020400.classiperlo.ui.contact

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.luca020400.classiperlo.R

class ContactPickerDialogFragment(private val listener: (Int) -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?) = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(R.string.contact_picker_dialog_title)
            .setItems(R.array.contact_options) { _, which ->
                // The 'which' argument contains the index position
                // of the selected item
                listener(which)
            }
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}