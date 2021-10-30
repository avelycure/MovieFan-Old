# MovieFan

Application for getting information about popular films from The Movie Database

## Technologies
* Kotlin
* Ktor
* MVVM
* Coroutines
* Coil
* Paging 3

## Functionality
First of all, the user goes to the main page of the application, which contains a list of films

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/main.jpg" width="320" />
</p>

The user can quickly scroll through the list and quickly receive an image thanks to Coil and Paging Library, while in the event of an Internet problem, the downloaded elements will not be lost.

If the user wants to see more detailed information about the film, he can click on it and get information about the country, companies, actors, budget and revenue. Also, for many films, the option to view a trailer is available.

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info1.jpg" width="320" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info2.jpg" width="320" />
</p>

The application follows the rules of attribution of The Movie Database, information about the API can be obtained by clicking on the button on the main screen of the application.

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/info.jpg" width="320" />
</p>

# Styling
The app's color theme adjusts automatically to the version selected on the device.

## Difficulties / resolved issues
While working with Ktor, I discovered an interesting fact. If the server sends a response in which there is no field, then I get an error <i>kotlinx.serialization.MissingFieldException</i>. Moreover, if you specify this field as nullable in the data class of the response, then the error will not disappear. Therefore, in order to solve this problem, I leave the field not-nullable and assign it a default value - an empty string.

## Atribution 
This product uses the TMDB API but is not endorsed or certified by TMDB.
