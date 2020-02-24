package com.luca020400.classiperlo.ui.contact

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.luca020400.classiperlo.BuildConfig
import com.luca020400.classiperlo.R
import okhttp3.*
import java.io.IOException

class ContactView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var callback: (String, String) -> Unit = { _: String, _: String -> }
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
        val passwordTextInputLayout: TextInputLayout = findViewById(R.id.view_contact_password)
        val textTextInputLayout: TextInputLayout = findViewById(R.id.view_contact_text)
        val materialButton: MaterialButton = findViewById(R.id.view_contact_button)
        materialButton.setOnClickListener {
            var error = false
            if ("abracadabra" != passwordTextInputLayout.editText?.text.toString()) {
                passwordTextInputLayout.error = "Password errata"
                return@setOnClickListener
            }

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
                .addFormDataPart("app", BuildConfig.VERSION_NAME)
                .addFormDataPart("autorizzo", "SI")
                .addFormDataPart("email", mailTextInputLayout.editText?.text.toString())
                .addFormDataPart("name", nameTextInputLayout.editText?.text.toString())
                .addFormDataPart("parola", "abracadabra")
                .addFormDataPart("selettore", selettore)
                .addFormDataPart("testo", textTextInputLayout.editText?.text.toString())
                .build()

            val request =
                Request.Builder().url("http://www.classiperlo.altervista.org/upload/upload.php")
                    .post(requestBody).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            context,
                            context.getString(R.string.contact_error_post),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            context,
                            context.getString(R.string.contact_done),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    callback(
                        mailTextInputLayout.editText?.text.toString(),
                        nameTextInputLayout.editText?.text.toString()
                    )
                }
            })
        }

        val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("saved", false)) {
            passwordTextInputLayout.editText?.setText("abracadabra")
            nameTextInputLayout.editText?.setText(
                sharedPreferences.getString("name", "")
            )
            mailTextInputLayout.editText?.setText(
                sharedPreferences.getString("mail", "")
            )
        }
    }
}