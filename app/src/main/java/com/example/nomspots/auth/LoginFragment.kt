package com.example.nomspots.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nomspots.MainActivity
import com.example.nomspots.R
import com.example.nomspots.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val view = binding.root

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            loginUser(
                binding.tiEmailLogIn.editText?.text.toString().trim { it <= ' ' },
                binding.tiPasswordLogIn.editText?.text.toString().trim { it <= ' ' }
            )
        }

        binding.tvDoNotHaveAccount.setOnClickListener {
            goSignUpFragment()
        }

        // Back pressed Logic for fragment
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    activity?.onBackPressed()
                    return@OnKeyListener true
                }
            }
            false
        })

        // Inflate the layout for this fragment
        return view
    }

    /**
     * Attempts to log in the user and authenticates the account through Google's Firestore
     * Authenticator.
     *
     * @param email email of the user account
     * @param password password of the user account
     */
    private fun loginUser(email: String, password: String) {
        Log.i(TAG, "Attempting to login user with email: $email")

        if (!validateForm(email, password)) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    goMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                if (!task.isSuccessful) {
                    binding.tvLoginStatus.visibility = View.VISIBLE
                }
            }
    }

    /**
     * Validates the submission form from the user and checks if there are invalid entries
     * for the email and password.
     *
     * @param email email of the user account
     * @param password password of the user account
     * @return true if all the entries are valid, false otherwise.
     */
    private fun validateForm(email: String, password: String): Boolean {
        var valid = true

        if (TextUtils.isEmpty(email)) {
            binding.tiEmailLogIn.error = "Please enter your Email."
            valid = false
        } else {
            binding.tiEmailLogIn.error = null
        }

        if (TextUtils.isEmpty(password)) {
            binding.tiPasswordLogIn.error = "Please enter your Password."
            valid = false
        } else {
            binding.tiPasswordLogIn.error = null
        }

        return valid
    }

    /**
     * Starts the Main Activity and finalizes the current one.
     */
    private fun goMainActivity() {
        val i = Intent(context, MainActivity::class.java)
        startActivity(i)
        activity?.finish()
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
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}