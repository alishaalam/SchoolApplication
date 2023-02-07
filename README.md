<h1 align="center">School Application</h1>

<p>
This project displays a list of schools. Clicking on any of the schools, opens a detail page about the school. 
The detail page displays - the name of the school, the dbn code, the SAT reading, writing & math score.

This app demonstrated the use of [Retrofit](https://square.github.io/retrofit/) to make REST requests to the 
web service, [Moshi](https://github.com/square/moshi) to handle the deserialization of the 
returned JSON to Kotlin data objects.

The app also leverages [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
[Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow),
[Data Binding](https://developer.android.com/topic/libraries/data-binding/) and [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) 
with the SafeArgs plugin for parameter passing between fragments.

The UI of the app has been kept simple, the focus of the work instead has been on architecting the app 
& following the guidelines which will help the app to scale.
Broadly speaking, this app can be separated into Data & Presentation/UI Layer.

The app uses a combination of the MVVM, Clean architecture, and Repository pattern to organize its code.
For data layer it follows the unidirectional flow, and also embraces the single source of truth principle. 
Dependency Injection is used to make the app testable and uncoupled from implementation. 

* MVVM separates the UI from the business logic, and Clean architecture takes this separation a step further by dividing the code into modules with specific responsibilities. 
This makes the code easier to maintain and modify.
* The Repository pattern is used to separate the data layer from the UI and domain layer.
* The app demonstrates the use of Retrofit to make REST requests to a web service, and Moshi to deserialize the JSON response into Kotlin data objects.
* For the UI, the app uses ViewModel, LiveData, and Data Binding with binding adapters. 
* For dependency injection, the app uses Hilt, which is a wrapper around Dagger.

The project handles the following additional scenarios -  
- Loading indicator when data is being fetched
- Error handling when the network is unavailable
- Retry button to retry the requests
</p>

I have also included test cases which test for data being loaded. More details in the Testing section

<p align="left">
<img width="236" alt="Screen Shot 2023-02-06 at 4 32 38 PM" src="https://user-images.githubusercontent.com/2645150/217118055-19633fb2-ac53-4d5f-a351-56e5258d8beb.png">
<img width="240" alt="Screen Shot 2023-02-06 at 4 30 00 PM" src="https://user-images.githubusercontent.com/2645150/217117705-779d164f-c94f-4d6e-8d9f-835d74fb3adf.png">
<img width="400" alt="Screen Shot 2023-02-06 at 3 25 20 PM" src="https://user-images.githubusercontent.com/2645150/217117422-55443069-51ac-452f-9443-9b6e8613e963.png">
</p>

## Architecture Overview

Once the user opens the app, it downloads the data for the first time & is cached for later use. 
The app currently includes a Room database. 
Its the responsibility of the class implementing SchoolRepository, to either download new data, or fetch it from the Room database. 
The repository class abstracts this logic away from the view model, keeping your code organized and decoupled.

* The data layer of the app encompasses remote & local datasources. Data is reconciled at the cache before being displayed to the user.
* The di layer contains modules which help to reduce coupling & increase testability
* The UI layer consists of the activity, fragments, the adapter for the recyclerview, the viewmodel and a class to represent the UI state.
* The util package contains a Resource file which is used to encapsulate Suceess, Error & Loading conditions in the application

This app demonstrates the use of:  
* Retrofit to make REST requests to the web service
* Moshi to handle the deserialization of the returned JSON to Kotlin data objects
* For UI - RecyclerView, ViewModel, StateFlow, Data Binding 
* For dependency injection it uses Hilt which is a wrapper around Dagger
* Kotlin Coroutines for async processing

<h3>MVVM architecture & Repository pattern</h1>

This app demonstrates the Repository pattern, the recommended best practice for code separation and architecture. Using
repository pattern the data layer is abstracted from the rest of the app. 
The data layer refers to the part of your app, separate from the UI, that handles the app's data and business logic, exposing consistent APIs for the rest of your app to access this data. While the UI presents information to the user, 
the data layer includes things like networking code, Room databases, error handling, and any code that reads or manipulates data.
A repository can resolve conflicts between data sources (such as persistent models, web services, and caches) and centralize changes to this data.

<p align="center">
<img width="690" alt="Screen Shot 2022-12-05 at 11 32 09 AM" src="https://user-images.githubusercontent.com/2645150/205726370-586c337a-182f-489f-9264-fbe8075357f2.png">
</p>

<h3>Clean Architecture</h1>
<p align="center">
<img width="378" alt="Screen Shot 2023-02-06 at 4 26 18 PM" src="https://user-images.githubusercontent.com/2645150/217117420-b6d8a7e4-8c5e-429b-b227-8179fa3007fa.png">
</p>

## Project Setup
1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

To observe the error conditions - 
1. Uninstall the app, if installed previously
2. Disable wifi/mobile network
3. Install the app
4. Observe that there is an error message and a retry button.

NOTE: If one first installs the app, with wifi/mobile data on, the data will be fetched and persisted on to the database. 
Any further attempt to repro error conditions won't work, because subsequent attempts to display the data will load from local storage if mobile network is unavailable.

## Testing
Added 3 unit test cases to ensure the data in the view is being loaded properly
<p align="center">
<img width="487" alt="Screen Shot 2023-02-06 at 11 59 25 AM" src="https://user-images.githubusercontent.com/2645150/217118845-9ec8d742-c143-44bf-8aa8-dfafa69d4cbc.png">
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 30
- Written in [Kotlin](https://kotlinlang.org/)
- Android Jetpack
  - [Navigation](https://developer.android.com/guide/navigation) for navigating between fragments
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) for observing data when lifecycle state changes
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) for UI Related data holder, lifecycle aware
  - [Databinding](https://developer.android.com/topic/libraries/data-binding) for view Binding + Bind data from view to ViewModel
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations
- [Retrofit](https://github.com/square/retrofit) for constructing the REST APIs
- [Moshi](https://github.com/square/moshi/) for A modern JSON library for Kotlin and Java
- [Hilt](https://dagger.dev/hilt/) for dependency injection


## Future Work 
1. Improve the user interface to make it more user-friendly and visually appealing.
    1. Add more details in the school list and school detail page
    2. Show the selection of items in the app, such as which items are currently selected.
    3. Add a pull-to-refresh feature, which allows the user to swipe down to refresh the data displayed in the app.
    4. Add an indicator to show if the app is currently online/offline
    5. Add a search bar on the top to make it easier to search schools
    6. Give the option to select schools as a favorite and see it separately.
2. Leverage Paging Library to fetch fixed amount of data based on user's scrolling of the list
3. Add Logging, Reporting & Analytics to understand app usage & performance
