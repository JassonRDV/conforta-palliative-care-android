# Conforta — Palliative Care Reference for Android

An Android reference application for browsing palliative care information by category and subcategory. The interface is built with Kotlin and Jetpack Compose, with remote JSON loading and local caching.

## Features in the source

- A grid of care categories.
- Subcategory navigation and a detail screen for descriptive content.
- JSON retrieval from a configured Google Drive file through Retrofit.
- Local storage through Room.
- A data-loading use case that reuses cached content for up to one day, refreshes it when needed, and falls back to cached data after a network failure.
- Loading and error state managed by a Hilt-injected ViewModel.

## Technology

Kotlin, Jetpack Compose, Material 3, Navigation Compose, ViewModel, StateFlow, coroutines, Hilt, Retrofit, Kotlin Serialization, and Room.

## Getting started

1. Clone this repository and open its root folder in Android Studio.
2. Install Android SDK 36 and use a Gradle JDK compatible with the project's Android Gradle Plugin.
3. Sync the Gradle project.
4. Select an emulator or device running Android 7.0 / API 24 or newer.
5. Run the `app` configuration.

The first content download requires internet access and access to the configured Google Drive JSON file. The default file ID is set in `MainViewModel.kt`. To supply another dataset, update that configuration and keep the JSON structure compatible with the domain models.

To request a debug build on Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Source guide

- [MainViewModel.kt](app/src/main/java/com/jasson/obesidade/MainViewModel.kt): loading state and default data source.
- [GetPalliativeCareDataUseCase.kt](app/src/main/java/com/jasson/obesidade/data/usecase/GetPalliativeCareDataUseCase.kt): refresh policy, cache fallback, and grouping.
- [ApiService.kt](app/src/main/java/com/jasson/obesidade/data/remote/ApiService.kt): Google Drive download endpoint.
- [MainScreen.kt](app/src/main/java/com/jasson/obesidade/ui/screen/mainscreen/MainScreen.kt): category grid.
- [CuidadoPaliativoDetalhe.kt](app/src/main/java/com/jasson/obesidade/ui/screen/detailscreen/CuidadoPaliativoDetalhe.kt): detail content.

## Development notes

The current Android application label is `Mente & Corpo`, and the package namespace is `com.jasson.obesidade`. The implemented content flow is centered on palliative care.

The separate repository streaming method, `getPalliativeCareDataStream()`, is still marked as a TODO. The current ViewModel loads content through the data-loading use case described above.
