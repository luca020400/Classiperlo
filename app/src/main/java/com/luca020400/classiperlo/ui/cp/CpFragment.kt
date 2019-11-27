package com.luca020400.classiperlo.ui.cp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luca020400.classiperlo.R
import com.luca020400.classiperlo.utils.DividerItemDecoration

class CpFragment : Fragment() {

    private val cpViewModel by viewModels<CpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cp, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view_cp)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.Orientation.Vertical
                )
            )
        }
        val adapter = CpAdapter()
        recyclerView.adapter = adapter
        cpViewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })
        return root
    }
}