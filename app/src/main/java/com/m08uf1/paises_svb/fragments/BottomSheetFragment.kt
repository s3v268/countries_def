package com.m08uf1.paises_svb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.m08uf1.paises_svb.R

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        val btnOption1 = view.findViewById<Button>(R.id.sheet_option1)
        btnOption1.setOnClickListener {
            dismiss()
        }

        return view
    }

}
