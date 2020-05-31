package com.luca020400.classiperlo.ui.setup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.DataItem

class SetupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val nameTextInputLayout: TextInputLayout
    private val mailTextInputLayout: TextInputLayout
    private val classTextInputLayout: TextInputLayout
    private val passwordTextInputLayout: TextInputLayout

    private lateinit var data: List<DataItem>

    init {
        View.inflate(context, R.layout.view_setup, this)
        nameTextInputLayout = findViewById(R.id.view_setup_name)
        mailTextInputLayout = findViewById(R.id.view_setup_mail)
        classTextInputLayout = findViewById(R.id.view_setup_class)
        passwordTextInputLayout = findViewById(R.id.view_setup_password)
    }

    fun addClasses(data: List<DataItem>) {
        this.data = data
        val adapter = ArrayAdapter(context, R.layout.list_item, data.map { it.name })
        (classTextInputLayout.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    fun checkData(): Boolean {
        if ("abracadabra" != passwordTextInputLayout.editText?.text.toString()) {
            passwordTextInputLayout.error = "Password errata"
            return false
        } else {
            nameTextInputLayout.error = null
        }

        if (nameTextInputLayout.editText?.text.isNullOrBlank()) {
            nameTextInputLayout.error = "Il nome e cognome non possono essere vuoti"
            return false
        } else {
            nameTextInputLayout.error = null
        }

        if (mailTextInputLayout.editText?.text.isNullOrBlank()) {
            mailTextInputLayout.error = "La mail non può essere vuota"
            return false
        } else {
            mailTextInputLayout.error = null
        }

        if (classTextInputLayout.editText?.text.isNullOrBlank()) {
            classTextInputLayout.error = "La classe non può essere vuota"
            return false
        } else {
            classTextInputLayout.error = null
        }

        val dataItem = data.first { it.name == classTextInputLayout.editText?.text.toString() }

        val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("name", nameTextInputLayout.editText?.text.toString())
            .putString("mail", mailTextInputLayout.editText?.text.toString())
            .putString("class", dataItem.url.url)
            .apply()

        return true
    }
}