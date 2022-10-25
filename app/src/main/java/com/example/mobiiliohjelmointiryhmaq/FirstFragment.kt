package com.example.mobiiliohjelmointiryhmaq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

// Testi!

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_first, container, false)
        val textField1=view.findViewById<TextView>(R.id.textView1)

        textField1.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToSecond)
        }

        return view
    }

}