# FolioControl Android App

The FolioControl Android App is a mobile application designed to manage real estate properties and
partnerships. It allows users to view, add, edit, and delete properties associated with different
partnerships.

## Features

- **User Authentication**: Secure user login and authentication process.
- **Partnerships**: View partnerships associated with the logged-in user.
- **Properties Management**: Add, edit, and delete properties within each partnership.
- **Premises Information**: Access information about premises associated with properties.
- **Documents Management**: Upload, view, and delete documents related to specific properties.

## Screenshots

### Dark Mode

#### Login Screen
![Login Screen](screenshots/Screenshot%202023-12-31%20010937.png)
*Caption: Start your journey with a secure and stylish login screen.*

#### Property Overview Screen
![Property Overview Screen](screenshots/Screenshot%202023-12-31%20011056.png)
*Caption: Easily manage your properties with a comprehensive overview.*

#### Property Delete Dialog
![Property Delete Dialog](screenshots/Screenshot%202023-12-31%20011158.png)
*Caption: Make informed decisions with a confirmation dialog for property deletion.*

#### Property Detail Screen
![Property Detail Screen](screenshots/Screenshot%202023-12-31%20011256.png)
*Caption: Dive into the details of each property, including its images, documents, and account information.*

#### Property Photos Screen
![Property Photos Screen](screenshots/Screenshot%202023-12-31%20011350.png)
*Caption: Property Photos Screen*

#### Property Documents Screen
![Property Documents Screen](screenshots/Screenshot%202023-12-31%20011626.png)
*Caption: Access and manage property-related documents with ease.*

#### Property Premises (Only for Apartments) Screen
![Property Premises Screen](screenshots/Screenshot%202023-12-31%20011719.png)
*Caption: Explore premises information specifically tailored for apartments.*

#### Property Account Details Screen
![Property Account Details Screen](screenshots/Screenshot%202023-12-31%20011550.png)
*Caption: Review and update account details associated with each property.*

### Light Mode

#### Login Screen
![Login Screen](screenshots/Screenshot%202023-12-31%20011851.png)
*Caption: Start your journey with a secure and stylish login screen.*

#### Property Overview Screen
![Property Overview Screen](screenshots/Screenshot%202023-12-31%20011752.png)
*Caption: Easily manage your properties with a comprehensive overview.*

#### Property Detail Screen
![Property Detail Screen](screenshots/Screenshot%202023-12-31%20011922.png)
*Caption: Dive into the details of each property, including its images, documents, and account information.*

#### Property Delete Dialog
![Property Delete Dialog](screenshots/Screenshot%202023-12-31%20012152.png)
*Caption: Make informed decisions with a confirmation dialog for property deletion.*

#### Property Photos Screen
![Property Photos Screen](screenshots/Screenshot%202023-12-31%20011949.png)
*Caption: Property Photos Screen*

#### Property Documents Screen
![Property Documents Screen](screenshots/Screenshot%202023-12-31%20012036.png)
*Caption: Property Documents Screen*

#### Property Premises (Only for Apartments) Screen
![Property Premises Screen](screenshots/Screenshot%202023-12-31%20012108.png)
*Caption: Explore premises information specifically tailored for apartments.*

#### Property Account Details Screen
![Property Account Details Screen](screenshots/Screenshot%202023-12-31%20012130.png)
*Caption: Review and update account details associated with each property.*

## Technologies Used

- **Kotlin**: The primary programming language used for Android app development.
- **Jetpack Compose**: Modern Android UI toolkit for building native UI.
- **Room Database**: Android's native SQLite database abstraction for data persistence.
- **Retrofit**: HTTP client for making network requests.
- **DownloadManager**: Android system service for handling downloads.

## Setup

1. Clone the repository: `git clone https://github.com/LexDarcoz/folio-control-android.git`
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.

## Configuration

- Update the `Constants.kt` file to set the appropriate base URLs for online and offline modes.
- Ensure proper server-side configuration for API endpoints.

## Dependencies

- [Compose Shimmer](https://github.com/marcinmoskala/compose-shimmer): Library for adding shimmer
  effect in Jetpack Compose.
- [Gson](https://github.com/google/gson): Library for JSON serialization and deserialization.
- [Retrofit](https://github.com/square/retrofit): HTTP client for Android.

## Tests

### Unit Tests

- **ViewModels**: Ensure correct behavior of viewmodels by testing their logic.

### UI Tests

- **User Flow**: Confirm that the user interface responds correctly to user interactions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


