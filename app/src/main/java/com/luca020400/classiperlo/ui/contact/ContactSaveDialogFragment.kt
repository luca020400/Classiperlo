package com.luca020400.classiperlo.ui.contact

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.luca020400.classiperlo.R

class ContactSaveDialogFragment(private val listener: (Boolean) -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?) = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(R.string.contact_save_dialog_title)
            .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                listener(true)
            }
            .setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int ->
            }
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}