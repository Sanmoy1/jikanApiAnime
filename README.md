# JikanAPI Anime App

A modern Android application that showcases anime information using the Jikan API. Built with Jetpack Compose and following clean architecture principles.

## 🌟 Features

- **Browse Top Anime**: Explore a curated list of top anime series with infinite scrolling
- **Detailed Information**: View comprehensive details about each anime including:
  - Synopsis and rating
  - Episode count
  - Genre information
  - Trailer playback (when available)
- **Modern UI**: Clean and intuitive interface built with Material 3 Design
- **Smooth Navigation**: Seamless transitions between screens
- **Efficient Loading**: Pagination support for smooth scrolling and data loading

## 🛠️ Technical Stack

### Architecture
- **MVVM (Model-View-ViewModel)** architecture pattern
- Clean Architecture principles
- Repository pattern for data management

### Libraries & Technologies
- **UI**
  - Jetpack Compose for modern UI development
  - Material 3 Design components
  - WebView for YouTube trailer playback
  - Glide for image loading

- **Networking**
  - Retrofit for API communication
  - Gson for JSON parsing
  - Jikan API v4 integration

- **Asynchronous Programming**
  - Kotlin Coroutines
  - Flow for reactive programming
  - StateFlow for UI state management

- **Pagination**
  - Jetpack Paging 3 for efficient data loading
  - Custom PagingSource implementation

### Key Components

1. **Data Layer**
   - Repository pattern for data abstraction
   - API response models
   - Paging source implementation

2. **Domain Layer**
   - Use cases for business logic
   - Repository interfaces
   - Domain models

3. **UI Layer**
   - Composable screens and components
   - ViewModels for state management
   - Navigation handling

## 🏗️ Architecture Overview

The app follows Clean Architecture principles:

```
app/
├── data/
│   ├── api/           # Retrofit API interfaces and response models
│   ├── model/         # Data models
│   └── repository/    # Repository implementation
├── di/                # Dependency injection modules
├── domain/
│   ├── model/         # Domain models
│   └── repository/    # Repository interfaces
├── ui/
│   ├── screens/       # Compose screens
│   │   ├── home/      # Anime list screen
│   │   └── detail/    # Anime detail screen
│   ├── components/    # Reusable compose components
│   ├── navigation/    # Navigation setup
│   └── theme/         # Existing theme package
└── util/              # Utility classes
```


