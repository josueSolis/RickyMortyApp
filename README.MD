# CharactersApp

This project is built using Kotlin Multiplatform Mobile (KMM) (https://kotlinlang.org/docs/multiplatform-get-started.html), leveraging SwiftUI for the iOS UI layer and Jetpack Compose for the Android UI layer.

## Requirements

- macOS (required for compiling the iOS app)
- Xcode >= 15
- Android Studio with Koala extension
- Kotlin 1.9.23
- Gradle 8.9
- Getting Started

### Build Artifacts:
```
./gradlew build
```

### Run share and android Tests:
```
./gradlew testDebugUnitTest
```

### Run on Android Device:
Use the "Run" button in Android Studio to launch the application on your target device.

### Run on iOS Device:
#### Generate the iOS shared framework:
```
./gradlew assemble
```
#### Open characterIosApp in Xcode.
In Xcode, navigate to the project settings and select the "General" tab.
Under "Frameworks, Libraries, and Embedded Content," click the "+" button and choose "Add Files."
Navigate to CharactersApp/shared/build/fat-framework/release and select the shared.framework folder.
In Xcode, you can now run the application in an emulator.

### Project Structure

The project consists of three main directories:

- characterAndroidApp: Contains native source code for the Android application's UI layer.
- characterIosApp: Contains native source code for the iOS application's UI layer.
- shared: Contains common code written in Kotlin (network layer and domain model).

### Code of Conduct

The UI layer of both applications follows a variant of the MVVM pattern (MVI).

#### Navigation:
iOS uses a coordinator pattern.
Android uses the Jetpack Navigation library.
#### Dependency Injection:
The Android app and shared module use the Koin library for dependency injection.
#### UI Design:
Both UIs strive to follow an atomic design approach, using small, reusable components for composition.

#### Contact

If you have any questions, please feel free to send an email to josuesolis33@gmail.com.