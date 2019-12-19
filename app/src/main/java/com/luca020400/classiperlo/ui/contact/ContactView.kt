package com.luca020400.classiperlo.ui.contact

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.luca020400.classiperlo.R
import okhttp3.*
import java.io.IOException

class ContactView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var option = ContactViewModel.Options.Message
        set(value) {
            field = value

            when (field) {
                ContactViewModel.Options.Message -> {
                    nameTextInputLayout.visibility = View.VISIBLE
                    mailTextInputLayout.visibility = View.VISIBLE
                }
                ContactViewModel.Options.NoMailMessage -> {
                    nameTextInputLayout.visibility = View.VISIBLE
                    mailTextInputLayout.visibility = View.GONE
                }
                ContactViewModel.Options.AnonymousMessage -> {
                    nameTextInputLayout.visibility = View.GONE
                    mailTextInputLayout.visibility = View.GONE
                }
            }
        }

    private val nameTextInputLayout: TextInputLayout
    private val mailTextInputLayout: TextInputLayout

    private val client = OkHttpClient()

    init {
        View.inflate(context, R.layout.view_contact, this)
        nameTextInputLayout = findViewById(R.id.view_contact_name)
        mailTextInputLayout = findViewById(R.id.view_contact_mail)
        val textTextInputLayout: TextInputLayout = findViewById(R.id.view_contact_text)
        val materialButton: MaterialButton = findViewById(R.id.view_contact_button)
        materialButton.setOnClickListener {
            var error = false
            if (nameTextInputLayout.editText?.text.isNullOrBlank() && option != ContactViewModel.Options.AnonymousMessage) {
                nameTextInputLayout.error = "Il nome e cognome non possono essere vuoti"
                error = true
            } else {
                nameTextInputLayout.error = null
            }

            if (mailTextInputLayout.editText?.text.isNullOrBlank() && option == ContactViewModel.Options.Message) {
                mailTextInputLayout.error = "La mail non può essere vuota"
                error = true
            } else {
                mailTextInputLayout.error = null
            }

            if (textTextInputLayout.editText?.text.isNullOrBlank()) {
                textTextInputLayout.error = "Il messaggio non può essere vuoto"
                error = true
            } else {
                textTextInputLayout.error = null
            }

            if (error) {
                return@setOnClickListener
            }

            val selettore = when (option) {
                ContactViewModel.Options.Message -> "Messaggio"
                ContactViewModel.Options.NoMailMessage -> "Nomail"
                else -> "Anonimo"
            }

            // Build the RequestBody
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("parola", "abracadabra")
                .addFormDataPart("name", nameTextInputLayout.editText?.text.toString())
                .addFormDataPart("email", mailTextInputLayout.editText?.text.toString())
                .addFormDataPart("testo", textTextInputLayout.editText?.text.toString())
                .addFormDataPart("selettore", selettore)
                .addFormDataPart("autorizzo", "SI")
                .build()

            val request =
                Request.Builder().url("http://www.classiperlo.altervista.org/upload/upload.php")
                    .post(requestBody).build()

            Toast.makeText(context, context.getString(R.string.contact_done), Toast.LENGTH_LONG)
                .show()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {}
            })
        }
    }
}