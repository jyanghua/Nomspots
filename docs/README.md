# First Kotlin Project - Nomspots

**Nomspots** is food finder app that will allow users to find food items they have never tried before.

[Click here to get the Android apk.](https://github.com/jyanghua/Nomspots/blob/master/docs/Nomspots-debug.apk).


Time spent (up until this day):

**2** hours setting up dependencies, Firebase configuration, adding dummy data

**6** hours learning Kotlin and reading documentation of libraries

**14** hours programming and debugging

## User Stories

- [x] User can sign up to create a new account using Firebase authentication.
- [x] User can log in and log out of his or her account.
- [x] The current signed in user is persisted across app restarts.
- [x] User can see the list of food items and swipe by dragging the cards to the left or the right
    - [x] The user can click on the buttons to automatically swipe the cards (Like and dislike)
    - [x] The user can undo the last swipe indefinitely

The following **optional** features are implemented:

- [x] Data binding was used to link with the UI elements
- [x] Swiping will show an overlay (emote) depending on the direction of the swipe.
- [x] Bottom navigation bar and multiple fragments for Home, Trending, and Profile.
- [x] Added some styling and custom icons/placeholder logo.
- [x] Multiple fragments for Login and Sign Up, and allows user to switch between the two options.
- [x] Added logout button to toolbar options menu under Profile tab
- [x] Custom toolbar for profile.
- [x] Added simple analytics when the user clicks on the Like, Dislike, Undo (Rewind) buttons.

TODO:

- [ ] Use Graphql and Apollo 2.0
- [ ] Add more sophisticated analytics and log meaningful user interaction.
- [ ] Better error handling for authentication screen (No empty fields, wrong credentials, etc.)
- [ ] Paginate the list of food items in the card stack view.
    - [ ] User can load more posts once he or she reaches the bottom of the feed using "infinite scrolling".
- [ ] Add a notice for the user upon reaching the end of the list (Currently blank).
- [ ] Upon liking a food item, add them to a list that can be browsed under user profile tab.
- [ ] Implements a much more complete user profile.
      - [ ] Allow the logged in user to add a profile photo.
- [ ] Glide image error handling and placeholders.
- [ ] Allow users to log in and register using a Google Account or any other social media account.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

User authentication:

<img src='https://github.com/jyanghua/Nomspots/blob/master/docs/gifs/nomspots_auth.gif' title='Video Walkthrough of Authentication' width='' alt='Video Walkthrough' />


Swiping items:

<img src='https://github.com/jyanghua/Nomspots/blob/master/docs/gifs/nomspots_swiping.gif' title='Video Walkthrough of Swiping the CardStackView' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Notes

The biggest challenge starting the project was understanding the differences between Java and Kotlin. Without any experience using Kotlin at first there were some errors and bugs that were cause by trying to implement some things the Java way. The component that was troubling to work with was the RecyclerView together with the CardStackView library which is what allows users to swipe the cards.

Another problem testing the Analytics part is how user logs are saved in a 24 hour timeframe and changes to the code can't be observed right away.


## Open-source libraries used

- [Google Firebase Authentication](https://firebase.google.com/docs/auth) - Easier management of user authentication
- [Google Cloud Firestore](https://firebase.google.com/docs/firestore) - NoSQL data storage
- [Google Analytics](https://firebase.google.com/docs/analytics) - Analytics for mobile development, allows logging events or user actions
- [CardStackView](https://github.com/yuyakaido/CardStackView) - Library for the swipeable card view for Android
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2020 Jack Yang Huang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.