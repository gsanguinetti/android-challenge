# Game of Thrones for Android Challenge
### Project Structure  
This project was organized using the principles of the [app modularization by features](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e). Although this approach is not necessary for an app as small as this challenge, it is useful to demonstrate how to structure a project that aims to build a robust and scalable application. This is a very common and good practice to keep different teams working on isolated and decoupled features. This application was divided in three "product features" packages:   
- **Characters**: This feature is the responsible for displaying the GoT Character list and filtering using the search interface.  
- **Houses**: This feature is the responsible for displaying the GoT House list.  
- **Home**: The main screen of the app, which loads the characters and houses tabs.
  
In addition to these packages, the project contains the following additional ones:  
- **base**: This is a base module which contains architecture base classes and helpers, which are used by the different product packages.   
- **data**: This module contains the network and local db communication logic between each product packages and the data source, bringing a common data source class with api call capabilities.  
 
  
### Architecture  
In order to maintain this code decoupled, testable and robust, the architecture of this app was designed using [clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html). Each Product Feature contains the following layers represented as packages in the src module folder:  
- **Domain Layer**: Contains the business logic of each module, totally independent of the device, the networking data or the ui. Each business logic task is represented as an *Use Case*.  
- **Repository Layer**: Represented by *Repositories* which have the responsibility to access and get data from the different sources (LocalDb and Networking).  
- **Presentation Layer**: Layer responsible for displaying the information provided by the business logic layer, using the UI interface. This layer use the [Google's architecture components](https://developer.android.com/topic/libraries/architecture/) approach to manipulate data between the activities/fragment and the presentation classes (represented by ViewModels).  
  
Each user flow which implements these three layers was developed using **async, event-based and reactive programming** with [RxJava](https://github.com/ReactiveX/RxJava).  
  
  
### Dependency Injection  
All the app component dependencies are injected using [Koin](https://insert-koin.io/), which provides a lightweight and pure Kotlin dependency injection mechanism with [glorious support for Android architecture classes such as ViewModels](https://insert-koin.io/docs/1.0/documentation/reference/index.html#_architecture_components_with_koin_viewmodel). Each module has an extra package called *module* who contains the injections of all the dependencies used by this module.  
  
### Unit Testing  
Each module contains a set of unit test classes that intends to test every component of the architecture. These test classes were designed to check the output of the class being tested, using [Mockito](https://site.mockito.org/) as a mocking mechanism of their inputs and dependencies.

### Challenge Considerations
The API got its images inaccessible. In this scenario, two different approaches would be taken from the app: show a placeholder or storage a default image for each character and house. Keep a default image stored in the app is a very bad practice because this increase severally the final apk size and should be considered fixed in server side.