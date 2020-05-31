package com.luca020400.classiperlo.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.utils.NetUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetupFragment : SlideFragment() {
    private lateinit var setupView: SetupView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_setup, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView = view.findViewById(R.id.view_setup)

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                NetUtils.getClasses()
            }
            setupView.addClasses(result)
        }
    }

    override fun canGoForward() = setupView.checkData()
}