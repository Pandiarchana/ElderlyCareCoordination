Elderly Care Coordination App

This is a Kotlin-based Android application developed to support caregivers and family members in managing the daily needs of elderly individuals. The app centralizes various functions like medication tracking, appointment scheduling, daily logging, emergency alerts, family member chat, and settings management.

Key Features:

Login Screen: Entry point for users (UI only, no backend auth).

Home Dashboard: Central hub to access all features.

Medication Tracker: Add and view medications.

Appointment Scheduler: Record and track upcoming appointments.

Daily Care Log: Add notes or records about day-to-day elderly care.

Emergency Alerts: Log emergency incidents for quick reference.

Family Member Section: Add and manage family profiles.

Chat Feature: WhatsApp-style family chat with profile images and simulated call options.

Settings Page: Placeholder screen to demonstrate future customization options.

Bottom Navigation: Allows easy access to home, back, and exit.

Technology Stack:

Language: Kotlin

UI Toolkit: Jetpack Compose + Material 3

Architecture: MVVM (Model-View-ViewModel)

Local Data Storage: Room Database

Navigation: Navigation Compose

State Management: ViewModel, State, LiveData

Build Tools: Android Studio, Gradle, Kotlin Coroutines

How to Run the App:

Clone the project:

bash
Copy
git clone https://github.com/Pandiarchana/ElderlyCareCoordination.git
Open it in Android Studio.

Let Gradle sync and build the project.

Run on an emulator or a physical device (API 21+).

Folder Structure Overview:

ui.screens/ – Contains all composable screens.

ui.screen/ – Contains chat and family list related UI.

viewmodel/ – ViewModels for managing app state.

data/ – Room database, DAO, and repository logic.

MainActivity.kt – Entry point with navigation graph.

resources/ – Icons and images used in the app.

GitHub: github.com/Pandiarchana

Disclaimer: This project was created for CP3406 – Mobile App Development (Assignment 2) and is intended for educational use only.
