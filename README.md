# Android exam | Random User

## Architecture Pattern
- Clean MVVM (Model-View-ViewModel)

## Libraries/Tools
- Retrofit
- Dagger Hilt
- Kotlin Coroutines
- Android Room Database
- Mockito
- JUnit
- NavGraph
- Paging Source
- ViewModel
- Flow
- Lifecycle

## About the Clean MVVM (Model-View-ViewModel)
It aims to separate the user interface logic from the business logic of the application, making it easier to maintain, test, and evolve.

The project is divided into four layers:
- Core
- Data
- Domain
- Presentation

### Core
Where the application and dependency injection modules located.

### Data
It contains the API interfaces, databases, and repository implementation.
It acts as an abstraction layer, shielding the data from direct interaction with the rest of the application.
Where the local and remote datasource is stored.

### Domain
Model (entity of data), Repository interfaces, and Use Cases are included in this layer.
UseCase for ViewModel access only.

### Presentation
Responsible for displaying the user interface and interacting with the user. It is typically implemented using UI elements like controls, layouts, and widgets.
The view observes the ViewModel and updates its appearance accordingly.