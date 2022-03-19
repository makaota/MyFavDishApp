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
 This app represent Splash Activity, Main Activity, AddUpdate Activity and four feature fragment views  
 1. All Dishes Fragment,
 2. Dish Details Fragment,
 3. Favorite Dishes Fragment,
 4. Random Dishes Fragment,

 # SPLASH ACTIVITY
 The splash animation is shown every time the app is opened\
![splash screen_300x600](https://user-images.githubusercontent.com/74915165/159121250-b8fa71f1-e1cc-454c-b7f2-8b1250ba409c.png)

 
 # MAIN ACTIVITY
  This Activity is used to hold all of our fragment views and also to hide and show the bottom navigation view and also to perform android notifications to the user of this app\
  ![main3_727x600 (3)](https://user-images.githubusercontent.com/74915165/159120950-31530264-4951-41d1-9575-c897d6ff5249.png)


 # ADD UPDATE ACTIVITY
 This Activity let's the user to add or update the dish details inside the database dish details include: 
 1 Dish Image
 2 Dish Title
 3 Dish Category
 4 Dish Ingredients
 5 Dish Cooking Time
 6 Dish Directions to cook\
![add dish_300x600](https://user-images.githubusercontent.com/74915165/159121225-786dbd2c-8e88-4c27-b3ab-1f414dc519b5.png)



 # 1. ALL DISHES FRAGMENT
 This Fragment let the user to filter added dishes using a filter menu of our dishes
 and also let the to add dishes using the add/plus menu icon that opens the ADD UPDATE ACTIVITY
 This Fragment also presents to the user a list of added dishes to our/local devices database
 each list is populated with a popup menu that lets the user to edit or delete the dish
 This Fragment uses navigation graphs to navigate to DISH DETAILS FRAGMENT to display dish details
 added to the database\
 ![all dishes_300x600](https://user-images.githubusercontent.com/74915165/159121276-b03ace9c-f691-42ef-b6b8-36f2148db7b6.png)


 # 2. DISH DETAILS FRAGMENT
 This Fragments displays the dish details to the user also let's the user to share the dish using share icon menu
 the user can choose to favorite/un-favorite the dish using heart icon
 if the user favorites the dish it will be displayed to the FAVORITE DISHES FRAGMENT
 This Fragment uses android palette to colorize the layout\
 ![dish details_300x600](https://user-images.githubusercontent.com/74915165/159121291-848ccff4-0080-4794-9c81-66d00e8e9d6d.png)


 # 3. FAVORITE DISHES FRAGMENT
 This Fragment displays a list of all favorites dishes that when clicked navigates back to the DISH DETAILS FRAGMENT\
 ![favorite dish_300x600](https://user-images.githubusercontent.com/74915165/159121303-c89f54ca-7222-4135-84ba-dedba4b9427f.png)


 # 4. RANDOM DISH FRAGMENT
 This Fragment uses spoonacular API to display random dishes to the user using our own dish details information, Uses Internet
 This Fragment also uses swipe to refresh to call the API for new random dish
 and the user can also choose to favorite the dish which will be added to the local database
 AND will be displayed inside the ALL DISHES FRAGMENT and FAVORITE DISHES FRAGMENT\
 ![random dish_300x600](https://user-images.githubusercontent.com/74915165/159121312-8449f75e-1539-452e-8b75-0b06a4f0f881.png)




 # Libraries used         
 * SDP - A scalable size unit library\
 https://github.com/intuit/sdp
 implementation 'com.intuit.sdp:sdp-android:1.0.6'

 * Dexter runtime permissions library\
 https://github.com/Karumi/Dexter
 implementation 'com.karumi:dexter:6.2.3'

 * Image loading library\
 For more detail visit the link: https://github.com/bumptech/glide
 implementation 'com.github.bumptech.glide:glide:4.13.0'\
 annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'

 * Room components\
 Reference Link: https://developer.android.com/training/data-storage/room
 def room_version = "2.4.2"\
 implementation "androidx.room:room-ktx:$room_version"\
 To use Kotlin annotation processing tool (kapt)\
 kapt "androidx.room:room-compiler:$room_version"\
 implementation 'androidx.palette:palette-ktx:1.0.0'

 * Retrofit\
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'\
 implementation "com.squareup.retrofit2:converter-gson:2.9.0"\
 implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'

 * RxJava\
 implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'\
 implementation 'io.reactivex.rxjava3:rxjava:3.0.0'

 * WorkManager\
 def work_version = "2.7.1"\
 Kotlin + coroutines\
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



