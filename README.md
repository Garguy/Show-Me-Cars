# Show-Me-Cars
The Show Me Cars is a simple Android application that allows users "search" for Cars in the Netherlands

## Features

- User can search for a city(only works for 2 in Netherlands currently) that will display a list of cars from SnappCar's API. 
- Loads an image of the car, price per day, and make/model of each car
- Did setup the search to handle other countries so more can be added if time allowed

## Technologies Used

- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs and getting network data.
- [Moshi](https://github.com/square/moshi) - for fast JSON parsing
- [Coil](https://coil-kt.github.io/coil/) - for image loading
- JetPack
    - Compose - A modern toolkit for building native Android UI.
    - Lifecycle - dispose observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
- Architecture
    - MVVM Architecture (View - ViewModel - Model)

## Getting Started

To get started with the Show Me Cars, follow these steps:

1. Clone the repository to your local machine
2. Open the project in Android Studio.
3. Build and run the project using an emulator or physical device.

## Wanted Changes

- Add more designed features such as
  - sorting
  - distance
  - multiple cities/countries

## Support

If you encounter any issues or have questions about the app, please contact [Adam](https://www.adamgardner.dev/contact).

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[LICENSE](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
