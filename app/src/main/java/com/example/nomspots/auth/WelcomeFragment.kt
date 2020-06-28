package com.example.nomspots.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nomspots.R
import com.example.nomspots.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )

        // Setting the button listeners
        binding.btnCreateNewAccountWelcome.setOnClickListener {
            goSignUpFragment()
        }

        binding.tvLogInWelcome.setOnClickListener {
            goLoginFragment()
        }

        return binding.root
    }

    /**
     * Switches to the Log In Fragment and removes the current fragment from the back
     * button stack.
     */
    private fun goLoginFragment() {
        activity!!.supportFragmentManager
            .popBackStack()
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_container, LoginFragment())
            .addToBackStack(LoginFragment.TAG)
            .commit()
    }

    /**
     * Switches to the Sign Up Fragment and removes the current fragment from the back
     * button stack.
     */
    private fun goSignUpFragment() {
        activity!!.supportFragmentManager
            .popBackStack()
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_container, SignUpFragment())
            .addToBackStack(SignUpFragment.TAG)
            .commit()
    }

    companion object {
        const val TAG = "WelcomeFragment"
    }

}