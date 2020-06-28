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
import com.example.nomspots.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )

        val view = binding.root

        // Initialize Firebase Auth
        auth = Firebase.auth


        // Setting the button listeners
        binding.btnCreateNewAccountSignUp.setOnClickListener {
            createAccount(
                binding.tiUsernameSignUp.editText?.text.toString().trim { it <= ' ' },
                binding.tiEmailSignUp.editText?.text.toString().trim { it <= ' ' },
                binding.tiPasswordSignUp.editText?.text.toString().trim { it <= ' ' }
            )
        }

        binding.tvAlreadyHaveAccount.setOnClickListener {
            goLoginFragment()
        }

        //Back pressed Logic for fragment
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
     * Attempts to create a new user account by Google's Firebase Authenticator and
     * will log in the user if successful.
     *
     * @param username username for the new account
     * @param email email of the user account
     * @param password password of the user account
     */
    private fun createAccount(username: String, email: String, password: String) {
        Log.i(TAG, "Attempting to create user $username")

        if (!validateForm(username, email, password)) {
            return
        }

        // Creates the user account with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    goMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        // Adds the Username to the created account
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
//            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
            .build()
        auth.currentUser?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Added the username to the account.")
                }
            }
    }

    /**
     * Validates the submission form from the user and checks if there are invalid entries
     * for the username, email, and password.
     *
     * @param username username for the new account
     * @param email email of the user account
     * @param password password of the user account
     * @return true if all the entries are valid, false otherwise.
     */
    private fun validateForm(username: String, email: String, password: String): Boolean {
        var valid = true

        if (TextUtils.isEmpty(username)) {
            binding.tiUsernameSignUp.error = "Please enter your Username."
            valid = false
        } else {
            binding.tiUsernameSignUp.error = null
        }

        if (TextUtils.isEmpty(email)) {
            binding.tiEmailSignUp.error = "Please enter your Email."
            valid = false
        } else {
            binding.tiEmailSignUp.error = null
        }

        if (TextUtils.isEmpty(password)) {
            binding.tiPasswordSignUp.error = "Please enter your Password."
            valid = false
        } else {
            binding.tiPasswordSignUp.error = null
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
     * Switches to the Login Fragment and removes the current fragment from the back
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

    companion object {
        const val TAG = "SignUpFragment"
    }
}