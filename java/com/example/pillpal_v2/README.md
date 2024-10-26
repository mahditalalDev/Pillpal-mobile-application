# PillPal: Pharmaceutical Assistant App

PillPal is a mobile application designed to enhance medication management and provide an easy-to-use platform for pharmacy customers in Lebanon. It aims to help users locate essential medicines, compare prices across nearby pharmacies, track their prescriptions, and set reminders for timely medication intake. Developed as a senior project, PillPal addresses the current challenges in Lebanon’s healthcare sector, particularly medication shortages and high drug costs, by leveraging technology to improve accessibility and efficiency.

## Features

- **Medication Search**: Easily search for medications across partnered pharmacies and locate the nearest available option.
- **Price Comparison**: View and compare medication prices across multiple pharmacies to find the best deals.
- **Medication Reminders**: Set reminders to ensure on-time medication intake.
- **Medication History**: Track your past medications and keep a record for future reference.
- **Pharmacist Interaction**: Chat with pharmacists for consultation or queries.
- **User Profile Management**: Each user has a profile with verified information, allowing for personalized services.
- **Doctor Profiles**: Browse doctor profiles, view contact information, and access professional services.

## Technology Stack

- **Frontend**: Android (Java/Kotlin)
- **Backend**: REST API using Firebase
- **Database**: Firebase Firestore for secure, real-time data handling

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/mahditalalDev/Pillpal-mobile-application.git
   cd Pillpal-mobile-application
   ```
2. **Set Up Firebase**:
   - Configure Firebase with your own credentials.
   - Add the `google-services.json` file to the `app/` directory.

3. **Build the Application**:
   - Open the project in Android Studio.
   - Sync and build the project.

4. **Run**:
   - Connect your Android device or use an emulator.
   - Run the app from Android Studio.

## Usage

- **User Registration**: Register as a patient or a pharmacy. Pharmacy accounts are subject to verification.
- **Search and Compare Medicines**: Use the search bar to find your medication and compare options by price and availability.
- **Set Medication Reminders**: Easily schedule reminders within the app.
- **View History**: Access a history of medications and reminders.

## System Design

- **System Architecture**: PillPal uses a multi-tiered client-server architecture to provide robust, scalable services.
- **Key Components**:
  - **User Authentication**: Firebase Authentication for secure login and account management.
  - **Pharmacy Management**: Enables pharmacies to manage inventories, track orders, and update medication availability.
  - **Patient Tools**: Medication reminders, search and comparison tools, and access to pharmacist consultations.

## Future Enhancements

- **Virtual Pharmacist Consultations**: Integrated telemedicine services to allow virtual consultations with pharmacists.
- **Expanded Medicine Database**: More comprehensive database with alternative and generic medicine recommendations.
- **International Expansion**: Expansion beyond Lebanon to other regions facing similar healthcare challenges.

## Contribution

Contributions are welcome! Please fork this repository and create a pull request with your proposed changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Let me know if you want any adjustments!