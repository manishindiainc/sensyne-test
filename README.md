# Sensyne Test App
This is sample app for Sensyne tech test

The app provide few functionalities like
* Load all hospital data from server if data is never fetched from server.

* If there is no network or data load from server fails, it reads data from Assets folder of App.

* In both the cases after first time reading, the hospital data is cached in DB and shown as the list

* On clicking any of the hospital info in list, hospital detail screen is shown.

* We also have filter floating action button on hospital list screen. Currently we support only two kinds of filter
  * No Filter - Complete list is shown
  * By NHS - All hospitals with sector as NHS shown

* Filter set by user is saved in shared preference and next time app is launched the previous filter setting is applied


   
## Assumptions:

 * The delimiter from server is found to be some non ascii value (-84). We convert this value to "tab(\t)".

 * Any other delimiter other than -84 or tab is not parsed.
 
 * Edge case like if server is not delimited with proper char(above assumption), the data may not be displayed properly
 
 * App is not tested with huge size of data. 


## Architecture:

The app design has tried to follow **clean architecture**


* **Presenter:** Contains Mainly AndroidUI component and lies on Outer Circle of Architecture

* **Usecase:** Defines different kind of use cases like DisplayHospitalList etc.

* **Data:** It's repository layer which is abstraction on database sources

* **FrameWork:** Android backend components which utlises Android networking APIs. It also contains any other Android helper
  components like Preference helper etc. Most of the time it has provided implementation for Data layer.
            
* **Domain:** Actual business logic layer, defines all the models.

## Test
* As of now only test cases have been added for HospitalDao
