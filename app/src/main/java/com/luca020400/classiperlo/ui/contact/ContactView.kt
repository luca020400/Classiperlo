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
    var option = ContactViewModel.Options.Message

    private val client = OkHttpClient()

    init {
        View.inflate(context, R.layout.view_contact, this)
        val textTextInputLayout: TextInputLayout = findViewById(R.id.view_contact_text)
        val materialButton: MaterialButton = findViewById(R.id.view_setup_button)
        materialButton.setOnClickListener {
            if (textTextInputLayout.editText?.text.isNullOrBlank()) {
                textTextInputLayout.error = "Il messaggio non può essere vuoto"
                return@setOnClickListener
            } else {
                textTextInputLayout.error = null
            }

            val selector = when (option) {
                ContactViewModel.Options.Message -> "Messaggio"
                ContactViewModel.Options.NoMailMessage -> "Nomail"
                else -> "Anonimo"
            }

            val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)

            // Build the RequestBody
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("app", BuildConfig.VERSION_NAME)
                .addFormDataPart("autorizzo", "SI")
                .addFormDataPart("email", sharedPreferences.getString("mail", "unknown")!!)
                .addFormDataPart("name", sharedPreferences.getString("name", "unknown")!!)
                .addFormDataPart("parola", "abracadabra")
                .addFormDataPart("selettore", selector)
                .addFormDataPart("testo", textTextInputLayout.editText?.text.toString())
                .build()

            val request =
                Request.Builder().url("http://www.classiperlo.altervista.org/upload/upload2.php")
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
                }
            })
        }
    }
}