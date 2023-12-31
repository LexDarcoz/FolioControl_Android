# FolioControl Android App

The FolioControl Android App is a mobile application designed to manage real estate properties and partnerships. It allows users to view, add, edit, and delete properties associated with different partnerships.

## Features

- **User Authentication**: Secure user login and authentication process.
- **Partnerships**: View partnerships associated with the logged-in user.
- **Properties Management**: Add, edit, and delete properties within each partnership.
- **Premises Information**: Access information about premises associated with properties.
- **Documents Management**: Upload, view, and delete documents related to specific properties.

## Screenshots

![Screenshot 1](screenshots/screenshot1.png)
*Caption for Screenshot 1*

![Screenshot 2](screenshots/screenshot2.png)
*Caption for Screenshot 2*

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

- [Compose Shimmer](https://github.com/marcinmoskala/compose-shimmer): Library for adding shimmer effect in Jetpack Compose.
- [Gson](https://github.com/google/gson): Library for JSON serialization and deserialization.
- [Retrofit](https://github.com/square/retrofit): HTTP client for Android.

## Tests

### Unit Tests

- **ViewModels**: Ensure correct behavior of viewmodels by testing their logic.

### UI Tests

- **User Flow**: Confirm that the user interface responds correctly to user interactions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


