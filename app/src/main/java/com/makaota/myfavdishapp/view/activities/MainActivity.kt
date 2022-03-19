package com.makaota.myfavdishapp.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.makaota.myfavdishapp.R
import com.makaota.myfavdishapp.databinding.ActivityMainBinding
import com.makaota.myfavdishapp.model.notification.NotifyWorker
import com.makaota.myfavdishapp.utils.Constants
import java.util.concurrent.TimeUnit


/**
 * Date Developed           : 29/01/2022
 * Date Modified            : 17/03/2022
 * Developer FullName       : Seotsa Abram Makaota
 * Developer ID             : 8506015231085
 * Project Name             : My Fav Dish App
 * Project Purpose          : To learn how to use the Android Jetpack suite and Coroutines.
 *                            Using Room, Palette, MVVM, Retrofit, Navigation and WorkManager
 *                            This has allowed me not only to learn new features of Android but also make sure that my code is up to date,
 *                            clean, uses best practices, and is easily maintainable.
 *                            A must for any developer who wants to call himself a senior developer.
 *
 *
 * About the app            : It's a Favorite Dishes Database Application
 *                            that uses an API to get cool random dishes that we can cook
 *                            and of course also stores dishes on the device using the ROOM Database to perform CRUD Operations
 *
 * Features                 : This app represent AddUpdate Activity, Main Activity, Splash Activity and
 *                            four feature fragments  1. All Dishes Fragment,
 *                                                    2. Dish Details Fragment,
 *                                                    3. Favorite Dishes Fragment,
 *                                                    4  Random Dishes Fragment,
 *
 *                            ADD UPDATE ACTIVITY
 *                            This Activity let's the user to add or update the dish details inside the database
 *                            dish details include: 1 Dish Image
 *                                                  2 Dish Title
 *                                                  3 Dish Category
 *                                                  4 Dish Ingredients
 *                                                  5 Dish Cooking Time
 *                                                  6 Dish Directions to cook
 *                            MAIN ACTIVITY
 *                            This Activity is used hold all of our fragment views and also
 *                            to hide and show the bottom navigation view
 *                            and also to perform android notifications to the user of this app
 *
 *                            SPLASH ACTIVITY
 *                            The splash animation is shown every time the app is opened
 *
 *                            1. ALL DISHES FRAGMENT
 *                            This Fragment let the user to filter added dishes using a filter menu of our dishes
 *                            and also let the to add dishes using the add/plus menu icon that opens the ADD UPDATE ACTIVITY
 *                            This Fragment also presents to the user a list of added dishes to our/local devices database
 *                            each list is populated with a popup menu that lets the user to edit or delete the dish
 *                            This Fragment uses navigation graphs to navigate to DISH DETAILS FRAGMENT to display dish details
 *                            added to the database
 *
 *                            2. DISH DETAILS FRAGMENT
 *                            This Fragments displays the dish details to the user also let's the user to share the dish using share icon menu
 *                            the user can choose to favorite/un-favorite the dish using heart icon
 *                            if the user favorites the dish it will be displayed to the FAVORITE DISHES FRAGMENT
 *                            This Fragment uses android palette to colorize the layout
 *
 *                            3. FAVORITE DISHES FRAGMENT
 *                            This Fragment displays a list of all favorites dishes that when clicked
 *                            navigates back to the DISH DETAILS FRAGMENT
 *
 *                            4. RANDOM DISH FRAGMENT
 *                            This Fragment uses spoonacular API to display random dishes to the user using our own dish details information,
 *                            Uses Internet
 *                            This Fragment also uses swipe to refresh to call the API for new random dish
 *                            and the user can also choose to favorite the dish which will be added to the local database
 *                            AND will be displayed inside the ALL DISHES FRAGMENT and FAVORITE DISHES FRAGMENT
 *
 *
 *
 * Libraries used           :  SDP - A scalable size unit library
 *                              https://github.com/intuit/sdp
 *                              implementation 'com.intuit.sdp:sdp-android:1.0.6'
 *
 *                              Dexter runtime permissions library
 *                              https://github.com/Karumi/Dexter
 *                              implementation 'com.karumi:dexter:6.2.3'
 *
 *                              Image loading library
 *                              For more detail visit the link: https://github.com/bumptech/glide
 *                              implementation 'com.github.bumptech.glide:glide:4.13.0'
 *                              annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
 *
 *
 *                              Room components
 *                              Reference Link: https://developer.android.com/training/data-storage/room
 *                              def room_version = "2.4.2"
 *
 *                              implementation "androidx.room:room-ktx:$room_version"
 *                              To use Kotlin annotation processing tool (kapt)
 *                              kapt "androidx.room:room-compiler:$room_version"
 *                              implementation 'androidx.palette:palette-ktx:1.0.0'
 *
 *                              Retrofit
 *                              implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 *                              implementation "com.squareup.retrofit2:converter-gson:2.9.0"
 *                              implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'
 *
 *                              RxJava
 *                              implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
 *                              implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
 *
 *                              def work_version = "2.7.1"
 *                              Kotlin + coroutines
 *                              implementation("androidx.work:work-runtime-ktx:$work_version")
 *
 *
 * What I have learned      : Android Jetpack suite
 *                            ViewBinding and Animation(Splash Screen)
 *                            MVVM (Model View ViewModel)
 *                            Permissions
 *                            Glide
 *                            ROOM Database and CRUD Operations
 *                            Coroutines, LiveData, Lifecycles and ViewModels
 *                            Navigation Component, Navigation Graph, Safe Args
 *                            Android Palette
 *                            Retrofit and RxJava
 *                            WorkManager, Sharing and Notifications
 *
 */


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        mNavController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_all_dishes, R.id.navigation_favorite_dishes,
                R.id.navigation_random_dish
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        navView.setupWithNavController(mNavController)

        // TODO Step 19: Handle the Notification when user clicks on it.
        // START
        if (intent.hasExtra(Constants.NOTIFICATION_ID)) {
            val notificationId = intent.getIntExtra(Constants.NOTIFICATION_ID, 0)
            Log.i("Notification Id", "$notificationId")

            // The Random Dish Fragment is selected when user is redirect
            // in the app via Notification.
            binding.navView.selectedItemId = R.id.navigation_random_dish
        }// END

        startWork()
    }

    /**
     * Constraints ensure that work is deferred until optimal conditions are met.
     *
     * A specification of the requirements that need to be met before a WorkRequest can run.
     * By default, WorkRequests do not have any requirements and can run immediately.
     * By adding requirements, you can make sure that work only runs in certain situations
     * - for example, when you have an unmetered network and are charging.
     */
    // For more details visit the link
    // https://medium.com/androiddevelopers/introducing-workmanager-2083bcfc4712
    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)  // if connected to WIFI
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)  // if the battery is not low
        .build()


    /**
     * You can use any of the work request builder that are available to use.
     * We will you the PeriodicWorkRequestBuilder as we want to execute the code periodically.
     *
     * The minimum time you can set is 15 minutes. You can check the same on the below link.
     * https://developer.android.com/reference/androidx/work/PeriodicWorkRequest
     *
     * You can also set the TimeUnit as per your requirement. for example SECONDS, MINUTES, or HOURS.
     */
    // setting period to 15 Minutes
    private fun createWorkRequest() = PeriodicWorkRequestBuilder<NotifyWorker>(
        15,
        TimeUnit.MINUTES)
        .setConstraints(createConstraints())
        .build()

    /** enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already exists,
    it will be kept if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced
    */
    private fun startWork(){
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("FavDish Notify Work",
                ExistingPeriodicWorkPolicy.KEEP,createWorkRequest())
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, null)
    }

    /**
     * A function to hide the BottomNavigationView with animation.
     */
    fun hideBottomNavigationView() {
        binding.navView.clearAnimation()
        binding.navView.animate().translationY(binding.navView.height.toFloat()).duration = 300
        binding.navView.visibility = View.GONE
    }

    /**
     * A function to show the BottomNavigationView with Animation.
     */
    fun showBottomNavigationView() {
        binding.navView.clearAnimation()
        binding.navView.animate().translationY(0f).duration = 300
        binding.navView.visibility = View.VISIBLE
    }
}