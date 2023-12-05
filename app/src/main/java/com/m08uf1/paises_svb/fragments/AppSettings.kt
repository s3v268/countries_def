package com.m08uf1.paises_svb.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ContentFrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.recreate
import com.m08uf1.paises_svb.MainActivity
import com.m08uf1.paises_svb.R
import com.m08uf1.paises_svb.databinding.FragmentAppSettingsBinding
import com.m08uf1.paises_svb.databinding.FragmentCountryListBinding
import com.m08uf1.paises_svb.databinding.FragmentCountryQuizBinding
import com.m08uf1.paises_svb.interfaces.CommunicatorInterface

class AppSettings : Fragment() {
    lateinit var binding: FragmentAppSettingsBinding
    lateinit var communicatorInterface: CommunicatorInterface
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentAppSettingsBinding.inflate(layoutInflater)

        //IMPORTANT POSAR-HO
        communicatorInterface = activity as CommunicatorInterface

        //setup shared prefs
        sharedPreferences = requireContext().getSharedPreferences("ac01Prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
      


        binding.btnTheme1.setOnClickListener{
            changeTheme(R.style.Base_Theme_Paises_svb)
        }
        binding.btnTheme2.setOnClickListener{
            changeTheme(R.style.Green_Theme_Paises_svb)
        }
        binding.btnTheme3.setOnClickListener{
            changeTheme(R.style.Blue_Theme_Paises_svb)
        }




        //IMPORTANTE ESTO VA AL FINAL
        return binding.root

    }

    private fun changeTheme(themeId: Int) {
        val crossfadeDuration = 500 // Duration in milliseconds

        val animation = AlphaAnimation(1f, 0f)
        animation.duration = crossfadeDuration.toLong()
        animation.fillAfter = true

        // Apply the fade-out animation to your activity's root view
        binding.fragSettingsMainlay.startAnimation(animation)

        // Delay the theme change and recreation to allow the fade-out animation to complete
        Handler().postDelayed({
            // Save the new theme ID in SharedPreferences
            editor.putInt("selectedTheme", themeId)
            editor.apply()

            // Restart the activity with the same class
            val intent = Intent(activity, requireActivity().javaClass)
            activity?.finish()
            activity?.startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }, crossfadeDuration.toLong())
    }

}