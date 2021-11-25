# MovieFan

## Main info

Application for getting information about films from The Movie Database:
* getting list of popular movies
* search movie by name
* get detailed information about the movie

## Project features
* Kotlin
* Kotlin Coroutines with Flow
* Clean Architecture(MVVM and use cases)
* Ktor for making requests to server
* ViewModel, StateFlow, Lifecycle
* Coil
* Dependency injection with Hilt
* Paging 3
* Firebase: Crashlytics
* [Material Prompts](https://github.com/sjwall/MaterialTapTargetPrompt) for better UX

## Functionality

### Popular movies
First of all, the user goes to the main page of the application, which contains a list of popular films

<p>
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/main.jpg" width="256" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info1.jpg" width="256" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/movie_info2.jpg" width="256" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/search.jpg" width="256" />
  <img src="https://github.com/avelycure/avelycure/blob/master/assets/movieFan/info.jpg" width="256" />
</p>

The user can quickly scroll through the list and quickly receive images thanks to Coil and Paging Library, while in the event of an Internet problem, the downloaded elements will not be lost. With LoadStateManager it is easy to show progress bar while loading images and show error messages if there are problems with internet connection.

### Search movie

We have two different requests: 
* get popular movies
* get movies by name

But the responses are the same! So we can use the same data class(Movie) in both requests and the same adapter. The only difference is that in fetching popular movies we use PopularPagingSource and in searching movies we use SearchPagingSource.

Searching movie with Flow is rather easy. We just make stateFlow from SearchView and use flow operators: 
* debounce(to prevent many calls)
* filter(to prevent calls with empty query)
* distincUntilChanged(not to call multiple times with the same query)
* flatMapLatest(to drop loading request if we got new one)

### Movie info

While watching main information about movies the user will probably want to see more detailed information about the film, so he can click on it and get information about the country, companies, actors, budget and revenue.

### YouTubePlayerSupportFragmentX

For many movies there is an option to watch a trailer. There is interesting thing about this case. To show a YouTube video you should use either YouTubeBaseActivity or YouTubePlayerSupportFragment.

I did not want to use more than one activity in my app, so I decided to use YouTubePlayerSupportFragment and found a problem: YouTubePlayerSupportFragment does not support androidx. I was already upset that I would have to create a new activity, when I found the solution for this problem [[here]](https://gist.github.com/medyo/f226b967213c3b8ec6f6bebb5338a492)

## Difficulties / resolved issues

### Ktor
While working with Ktor, I discovered an interesting fact. If the server sends a response in which there is no field, then I get an error <i>kotlinx.serialization.MissingFieldException</i>. Moreover, if you specify this field as nullable in the data class of the response, then the error will not disappear. Therefore, in order to solve this problem, I leave the field not-nullable and assign it a default value - an empty string.

### StateFlow
I found interesting case while using StateFlow. In state of MovieInfo fragment I use field which is a Query(custom class, not Java) of errors. And if I try to change stateFlow like this:
```kotlin
val queue = state.value.errorQueue
queue.add(uiComponent)
state.value = state.value.copy(errorQueue = queue)
```
The state does not emit nothing, because as said in docs of StateFlow:
```kotlin
/** Atomically compares the current value with expect and sets it to update if it is equal to expect. The
* result is true if the value was set to update and false otherwise.
* This function use a regular comparison using Any.equals. If both expect and update are equal to the
* current value, this function returns true, but it does not actually change the reference that is stored in
* the value.
* This method is thread-safe and can be safely invoked from concurrent coroutines without external
* synchronization.
*/
public fun compareAndSet(expect: T, update: T): Boolean
```
So in my Query class it just compare links, and as the links are the same it emits nothing. I had many ideas(override equals to return query length or always false) but this will probably lead to difficult bugs, so I made a simple thing: 
```kotlin
val queue: Queue<UIComponent> = Queue(mutableListOf())
        for (i in 0 until _state.value.errorQueue.count())
            _state.value.errorQueue.poll()?.let { queue.add(it) }
        queue.add(uiComponent)
        _state.value = _state.value.copy(errorQueue = queue)
 ```
## Atribution 
The application follows the rules of attribution of The Movie Database, information about the API can be obtained by clicking on the button on the main screen of the application.
