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

# Dark Mode

![Login Screen](screenshots/Screenshot%202023-12-31%20010937.png)
*Login Screen*

![Property Overview Screen](screenshots/Screenshot%202023-12-31%20011056.png)
*Property Overview Screen*

![Property Delete dialog](screenshots/Screenshot%202023-12-31%20011158.png)
*Property Delete dialog*

![Property Detail Screen](screenshots/Screenshot%202023-12-31%20011256.png)
*Property Detail Screen*

![Property Photos Screen](screenshots/Screenshot%202023-12-31%20011350.png)
*Property Photos Screen*

![Property Documents Screen](screenshots/Screenshot%202023-12-31%20011626.png)
*Property Documents Screen*

![Property Premises (Only for apartments) Screen](screenshots/Screenshot%202023-12-31%20011719.png)
*Property Premises (Only for apartments) Screen*

![Property Account Details Screen](screenshots/Screenshot%202023-12-31%20011550.png)
*Property Account Details Screen*

# Light Mode

![Login Screen](screenshots/Screenshot%202023-12-31%20011851.png)
*Login Screen*

![Property Overview Screen](screenshots/Screenshot%202023-12-31%20011752.png)
*Property Overview Screen*

![Property Delete dialog](screenshots/Screenshot%202023-12-31%20012152.png)
*Property Delete dialog*

![Property Detail Screen](screenshots/Screenshot%202023-12-31%20011922.pn)
*Property Detail Screen*

![Property Photos Screen](screenshots/Screenshot%202023-12-31%20011949.png)
*Property Photos Screen*

![Property Documents Screen](screenshots/Screenshot%202023-12-31%20012036.png)
*Property Documents Screen*

![Property Premises (Only for apartments) Screen](screenshots/Screenshot%202023-12-31%20012108.png)
*Property Premises (Only for apartments) Screen*

![Property Account Details Screen](screenshots/Screenshot%202023-12-31%20012130.png)
*Property Account Details Screen*

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


