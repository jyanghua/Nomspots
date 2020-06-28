package com.example.nomspots.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.nomspots.R
import com.example.nomspots.adapters.FoodItemAdapter
import com.example.nomspots.databinding.FragmentHomeBinding
import com.example.nomspots.models.FoodItem
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.*

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentHomeBinding
    private lateinit var allItems: MutableList<FoodItem>
    private lateinit var adapter: FoodItemAdapter
    private lateinit var manager: CardStackLayoutManager
    private lateinit var csv: CardStackView
    private lateinit var analytics: FirebaseAnalytics
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Firebase Auth
        auth = Firebase.auth

        // Obtain the FirebaseAnalytics instance.
        if (container != null) {
            analytics = FirebaseAnalytics.getInstance(container.context)
        }

        // Initialize Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        // CardStackView initialization
        csv = binding.cardStack
        allItems = mutableListOf()

        // CardStackView configuration
        manager = CardStackLayoutManager(context)
        manager.setStackFrom(StackFrom.Left)
        manager.setTranslationInterval(4.0f)
        manager.setVisibleCount(3)

        queryItems()

        adapter = FoodItemAdapter()
        adapter.setItems(allItems)

        // Bind the data to the Adapter and View Holder
        csv.layoutManager = manager
        csv.adapter = adapter
        csv.itemAnimator = DefaultItemAnimator()

        setListeners()

//        if (manager.topPosition == adapter.itemCount - 5) {
//            paginate();
//        }
//        var csl: CardStackListener

        return binding.root
    }

    /**
     * Queries the items from Google Firestore and retrieves the data for each of the food items.
     */
    private fun queryItems() {
        // Access a Cloud Firestore instance
        val db = FirebaseFirestore.getInstance()

        db.collection("items")
            .limit(10)
            .get()
            .addOnSuccessListener { result ->

                // Converts the data from Firestore to Java Objects
                val items = result.toObjects(FoodItem::class.java)

//                adapter.clear()
                allItems.addAll(items)
                adapter.notifyDataSetChanged()

                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /**
     * Sets all the on click listeners for the following buttons:
     * - Like (Automatic swipe)
     * - Dislike (Automatic swipe)
     * - Undo or Rewind
     */
    private fun setListeners() {
        binding.btnLike.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            csv.swipe()
            logEvent("Like button")

            Log.d(TAG, "Top" + manager.topPosition)
        }

        binding.btnDislike.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            csv.swipe()
            logEvent("Dislike button")

            Log.d(TAG, "Top" + manager.topPosition)
        }

        binding.btnRewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            csv.rewind()
            logEvent("Rewind button")

            Log.d(TAG, "Top" + manager.topPosition)
        }
    }

    /**
     * Logs a specific event or action and sends it to Firebase Analytics.
     * @param name name of the event or action to be logged.
     */
    private fun logEvent(name: String) {
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}