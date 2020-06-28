package com.example.nomspots.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nomspots.MainActivity
import com.example.nomspots.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth = Firebase.auth

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.auth_container, WelcomeFragment())
            commit()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Checks if the user is signed in (non-null) and redirects to the Main Activity.
        if (auth.currentUser != null) {
            Log.d(TAG, "User is signed in. Switching to the main activity.")
            goMainActivity()
        }
    }

    /**
     * Starts the Main Activity and finalizes the current one.
     */
    private fun goMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    companion object {
        const val TAG = "AuthActivity"
    }
}