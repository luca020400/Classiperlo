package com.luca020400.classiperlo.ui.setup

import android.Manifest
import android.os.Bundle
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide
import com.luca020400.classiperlo.R

class SetupActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(
            SimpleSlide.Builder()
                .title("Classiperlo")
                .description("Classiperlo")
                .image(R.drawable.ic_launcher)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(false)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .build()
        )
        addSlide(
            FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(SetupFragment())
                .build()
        )
        addSlide(
            SimpleSlide.Builder()
                .title(R.string.sei_pronto_titolo)
                .description(R.string.sei_pronto_desc)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .build()
        )
    }
}