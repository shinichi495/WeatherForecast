# WeatherForecast
- This is an exam for interview. It use Android Architecture Component with Dagger 2
# Introduction 
- This app just have only 1 screen on this app  - MainFragment
    - This screen allow user can get a weather from location by input on this screen.
    - When user input and click button "Get Weather", we will check from local database, if do not have data, we will continue call to server and get infromation from it, after that 
   we will save data to local if get successfully
# Software development principles, pattern , practice ...
- I use MVVM Model to development this app
    - Model layer to keep Object (which will show on layout, save on DB ...)
    - ViewModel that provide data to UI component (MainFragment), and handling bussiness flow to communication with Model. I also used Livedata to observable between layers (ViewModel - View ... )
    - View that use binding data from UI and ViewModel
    - Repository will allow how data flow active (NetworkBoundResource) and view model will use it to get data from database or server (WeatherRepository)
    - Room will provide a way to communication with database, it include data access object , Entities (WeatherDb), Room DB (AppDB) ...
    - Excutor will provider background and main thread which serve for some task related with IO task, network task, after that it will use Handle which send 
    message to main thread
    
# Third party 
- Retrofit : to call Api from server
- Dagger 2 : dependency injection framework to generation code through base anotation, avoid number of function ....

# Checklist
- Programming language: Kotlin is required, Java is optional. 
- Design app's architecture (suggest MVVM)
- Apply LiveData mechanism
- UI should be looks like in attachment.
- Exception handling

#Step to run local 
- Currently I dev on AndroidStudio 4.0 (Android Studio 4.0 Build #AI-193.6911.18.40.6514223, built on May 20, 2020)
- Get repo and run normal with debug mode (perhap must update gradle if it's too old)
                                        