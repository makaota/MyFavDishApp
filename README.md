# MyFavDishApp
Date Developed           : 29/01/2022

Date Last Modified       : 18/03/2022

Developer FullName       : Seotsa Abram Makaota

Project Name             : My Fav Dish App

# Project Purpose          
To learn how to use the Android Jetpack suite and Coroutines, using Room, Palette, MVVM, Retrofit, Navigation and WorkManager.
This has allowed me not only to learn new features of Android but also make sure that my code is up to date,
clean, uses best practices, and is easily maintainable.
A must for any developer who wants to call himself a senior developer.
 
 
 # About the app            
 It's a Favorite Dishes Database Application that uses an API to get cool random dishes that we can cook
 and of course also stores dishes on the device using the ROOM Database to perform CRUD Operations
 
 # Features           
 This app represent AddUpdate Activity, Main Activity, Splash Activity and three feature fragments 
 1. All Dishes Fragment,
 2. Dish Details Fragment,
 3. Favorite Dishes Fragment,
 4  Random Dishes Fragment,

 # ADD UPDATE ACTIVITY
 This Activity let's the user to add or update the dish details inside the database dish details include: 
 1 Dish Image
 2 Dish Title
 3 Dish Category
 4 Dish Ingredients
 5 Dish Cooking Time
 6 Dish Directions to cook
 # MAIN ACTIVITY
 This Activity is used to hide and show the bottom navigation view and also to perform android notifications to the user of this app

 # SPLASH ACTIVITY
 The splash animation is shown every time the app is opened

 # 1. ALL DISHES FRAGMENT
 This Fragment let the user to filter added dishes using a filter menu of our dishes
 and also let the to add dishes using the add/plus menu icon that opens the ADD UPDATE ACTIVITY
 This Fragment also presents to the user a list of added dishes to our/local devices database
 each list is populated with a popup menu that lets the user to edit or delete the dish
 This Fragment uses navigation graphs to navigate to DISH DETAILS FRAGMENT to display dish details
 added to the database

 # 2. DISH DETAILS FRAGMENT
 This Fragments displays the dish details to the user also let's the user to share the dish using share icon menu
 the user can choose to favorite/un-favorite the dish using heart icon
 if the user favorites the dish it will be displayed to the FAVORITE DISHES FRAGMENT
 This Fragment uses android palette to colorize the layout

 # 3. FAVORITE DISHES FRAGMENT
 This Fragment displays a list of all favorites dishes that when clicked navigates back to the DISH DETAILS FRAGMENT

 # 4. RANDOM DISH FRAGMENT
 This Fragment uses spoonacular API to display random dishes to the user using our own dish details information, Uses Internet
 This Fragment also uses swipe to refresh to call the API for new random dish
 and the user can also choose to favorite the dish which will be added to the local database
 AND will be displayed inside the ALL DISHES FRAGMENT and FAVORITE DISHES FRAGMENT



 # Libraries used         
 * SDP - A scalable size unit library
 https://github.com/intuit/sdp
 implementation 'com.intuit.sdp:sdp-android:1.0.6'

 * Dexter runtime permissions library
 https://github.com/Karumi/Dexter
 implementation 'com.karumi:dexter:6.2.3'

 * Image loading library
 For more detail visit the link: https://github.com/bumptech/glide
 implementation 'com.github.bumptech.glide:glide:4.13.0'
 annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'


 * Room components
 Reference Link: https://developer.android.com/training/data-storage/room
 def room_version = "2.4.2"

 implementation "androidx.room:room-ktx:$room_version"
 To use Kotlin annotation processing tool (kapt)
 kapt "androidx.room:room-compiler:$room_version"
 implementation 'androidx.palette:palette-ktx:1.0.0'

 * Retrofit
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation "com.squareup.retrofit2:converter-gson:2.9.0"
 implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'

 * RxJava
 implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
 implementation 'io.reactivex.rxjava3:rxjava:3.0.0'

 def work_version = "2.7.1"
 Kotlin + coroutines
 implementation("androidx.work:work-runtime-ktx:$work_version")


 # What I have learned
 * Android Jetpack suite
 * ViewBinding and Animation(Splash Screen)
 * MVVM (Model View ViewModel)
 * Permissions
 * Glide
 * ROOM Database and CRUD Operations
 * Coroutines, LiveData, Lifecycles and ViewModels
 * Navigation Component, Navigation Graph, Safe Args
 * Android Palette
 * Retrofit and RxJava
 * WorkManager, Sharing and Notifications

