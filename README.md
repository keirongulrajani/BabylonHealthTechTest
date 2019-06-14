# TechTest - Keiron Gulrajani

As per the instructions listed on [this code challenge](https://github.com/Babylonpartners/android-playbook/blob/master/recruitment/code_challenge.md), I have construced an app with  two screens, as follows:
- A screen with a list of clickable posts with the following information:
    - Post title
    - Author name
    - Generated avatar    
    - Number of comments
- A detail screen for the selected post with the following information:
    - Post title
    - Post body
    - Author name
    - Author generated avatar
    - list of comments with:
        - comment author
        - comment author generated avatar
        - comment name
        - comment body

The post information is fetched from the following API endpoints as per the instructions:
- GET http://jsonplaceholder.typicode.com/posts
- GET http://jsonplaceholder.typicode.com/users 
- GET http://jsonplaceholder.typicode.com/comments 
 
And also the random avatars for the posts authors are generated with:
- https://api.adorable.io/avatars

Most of my time was spent setting up the project architecture, as I believe this is the most important aspect of any project. The architecture I used is the [clean architecture pattern](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) with [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) using the Android Architecture components. This approach meant the initial effort was large, but it pays off in terms of ease of feature addition, and ease of maintenance due to proper separation/modularity within the code. The data is loaded in the `data_` modules using `DataSource` classes (injected with clients to make the network calls). The `domain_` layer has repository interfaces which return domain models, and the data layer contains the implementation of the repository interfaces with mappers to translate from data to domain models. There is also defined common domain models (`PostDetails`, `UserDetails` and `CommentDetails`) which are mapped to before passing to the UI, and the UI maps these models with presentation mappers into UIModels for display.

In terms of third party libraries in the project I used Refrofit 2/Okhttp 3 for the network calls, Dagger 2 for dependency injection, also RxJava 2 is used a lot for the use cases and mappings, and also the android architecture components for the ViewModels. I also used Glide for image loading, and Lottie to add nice loading animations from vector graphics.

Though most of the app is unit tested, given more time I would have liked to add Robolectric tests for the Fragments/Adapters/ViewHolders, and perhaps even Espresso tests for the UI. 
Also, given more time i would have liked to concentrate a bit more on the UI side of things, perhaps explore nice transitions between the main/details screen with some shared elements. The design is very basic (just text of different sizes with colours) so some better thought into the list items and screen layout design would definitely improve the looks.
