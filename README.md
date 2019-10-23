# Question App [![Build Status](https://app.bitrise.io/app/92517aef38eccdc5/status.svg?token=433z4EdOThnK3aqcI1W1CQ)](https://app.bitrise.io/app/92517aef38eccdc5)

This project allows users to search, answer and share questions with friends.

## Project organization

The project is organized in two main packages **core** and **questions**. 

### Core

The core has all the basic functionalities that can be reused on all features of the system. Such as utility classes and network base classes.

### Questions

This package is the main feature of the app. Inside of it contains all the subfeatures like **healthcheck**, **list**, **details** and **share**. Also, a package called **commons** was created to have all the classes that are shared by the subfeatures.

### Feature / Subfeature

Each feature/subfeature is organized using the following structure:

* data - contains all the classes related to data
* di - contain the module configuration to be used by koin
* model - contain model classes of the feature
* ui - contain all the classes related to the UI layer of the app.
    * view - contain all the fragments or activities of the feature.
    * adapters - contain the adapters used by the fragments and activities.
* viewmodel - contain all the view models of the features/subfeature.

## Configuration 

To configure the project you have two options:

* Option 1: Download using **git** doing the following command: 

```
    git clone https://github.com/asleao/questions-app.git
```

* Option 2: Download the [zip](https://github.com/asleao/questions-app/archive/master.zip) file and extract it on the folder of your preference.

After doing one of the steps above, just open the project on **android studio**. 

That's it.

Enjoy!
