Top Android Repo with Android Architecture Components
===========================================================

This is a test app that uses Android Architecture Components with Dagger 2.

**NOTE** It is a relatively more complex and complete example so if you are not familiar
with [Architecture Components][arch], you are highly recommended to check other examples
in this repository first.


### Functionality
The app is composed of 2 main screens.
#### RepoListFragment
Allows you to search repositories on Github.
Each search result is kept in the database in `Repo` table where
the list of repository.

Search query params like q as `Android`, sort as `stars or updated` and per_page limit `50`

#### DetailFragment
This fragment displays the details of a repository and its owner.
Like as `name`, `avater`, `updated date` and `repository description`

### Building
You can open the project in Android studio and press run.


### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Android View Binding][view-binding]
* [Dagger 2][dagger2] for dependency injection
* [Retrofit][retrofit] for REST api communication
* [Coil][coil] for image loading
* [espresso][espresso] for UI tests
* [mockito][mockito] for mocking in tests

[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[view-binding]: https://developer.android.com/topic/libraries/view-binding/index.html
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[coil]: https://github.com/coil-kt/coil
[mockito]: http://site.mockito.org
