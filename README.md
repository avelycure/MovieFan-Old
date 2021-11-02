# MovieFan

Application for getting information about popular films from The Movie Database

## Project features
* Kotlin
* Kotlin Coroutines with Flow
* Clean Architecture
* Ktor for making requests to server
* ViewModel, StateFlow, Lifecycle
* Coil
* Dependency injection with Hilt
* Paging 3

## Functionality
First of all, the user goes to the main page of the application, which contains a list of popular films

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/main.jpg" width="256" />
</p>

The user can quickly scroll through the list and quickly receive images thanks to Coil and Paging Library, while in the event of an Internet problem, the downloaded elements will not be lost. With LoadStateManager it is easy to show progress bar while loading images and show error messages if there are problems with internet connection.

To prevent slow loading of movies when user rotates the screen we use Hilt to inject MovieAdapter.

We have two different requests: 
* get popular movies
* get movies by name
But the responses are the same! So we can use the same data class(Movie) in both requests and the same adapter. The only difference is that in fetching popular movies we use PopularPagingSource and in searching movies we use SearchPagingSource.

While watching main information about movies the user will probably want to see more detailed information about the film, so he can click on it and get information about the country, companies, actors, budget and revenue.

## YouTubePlayerSupportFragmentX

For many movies there is an option to watch a trailer. There is interesting thing about this case. To show a YouTube video you should use either YouTubeBaseActivity or YouTubePlayerSupportFragment.

I did not want to use more than one activity in my app, so I decided to use YouTubePlayerSupportFragment and found a problem: YouTubePlayerSupportFragment does not support androidx. I was already upset that I would have to create a new activity, when I found the solution for this problem [[here]](https://gist.github.com/medyo/f226b967213c3b8ec6f6bebb5338a492)

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info1.jpg" width="256" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info2.jpg" width="256" />
</p>

# Styling
The app's color theme adjusts automatically to the version selected on the device.

## Difficulties / resolved issues
While working with Ktor, I discovered an interesting fact. If the server sends a response in which there is no field, then I get an error <i>kotlinx.serialization.MissingFieldException</i>. Moreover, if you specify this field as nullable in the data class of the response, then the error will not disappear. Therefore, in order to solve this problem, I leave the field not-nullable and assign it a default value - an empty string.

## Atribution 
The application follows the rules of attribution of The Movie Database, information about the API can be obtained by clicking on the button on the main screen of the application.

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/info.jpg" width="256" />
</p>
