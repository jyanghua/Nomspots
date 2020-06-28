package com.example.nomspots.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.nomspots.R
import com.example.nomspots.auth.AuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var actionBar: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Find the toolbar view inside the activity layout
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_profile)
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        (Objects.requireNonNull(activity) as AppCompatActivity).setSupportActionBar(
            toolbar
        )

        // Display logo and set clickable
        actionBar =
            (Objects.requireNonNull(activity) as AppCompatActivity).supportActionBar
//        actionBar?.title = auth.currentUser?.displayName
        setHasOptionsMenu(true)
    }

    /**
     * Initializes the toolbar view.
     */
    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        super.getActivity()!!.menuInflater.inflate(R.menu.profile_toolbar, menu)
    }

    /**
     * Selection listener for the toolbar's dropdown menu items.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_menu -> {
                signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Attempts to sign out the user from the current session via Google's Firebase
     * Authenticator. Upon success, returns the user to the Authentication Activity.
     */
    private fun signOut() {
        auth.signOut()
        val i = Intent(context, AuthActivity::class.java)
        startActivity(i)
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}