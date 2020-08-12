# Overview
This app is a digital CV with real data.

# Architecture
This app uses Android dynamic features modules for modularisation. New module should be created in following cases:
* For new api service
* For new api service modules
* For new ui section

# Running
In order to run this app in Android Studio, you need to create run configuration using `app` module

# Possible improvements
Due to lack of time I had to do some compromises. In next release I would improve:

* Custom`CoordinatorLayout.Behavior` for avatar inside app bar. I would implement animation which transforms big avatar into the smaller one.
* Better error handling. For now I just show `Snackbar` when there is some exception.
* Loading with skeletons instead of simple overlay.
