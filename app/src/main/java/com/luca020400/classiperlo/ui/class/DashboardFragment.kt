package com.luca020400.classiperlo.ui.`class`

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.classes.Url
import java.net.URL

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController(view)
        val sharedPreferences = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val action = DashboardFragmentDirections.dashboardToWebview(
            Url(sharedPreferences.getString("class", "")!!)
        )
        navController.navigate(action)
    }
}