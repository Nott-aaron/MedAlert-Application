# 💊 MedAlert – Medicine Reminder Android Application

## 📌 Lab Test 1

**Student Name:** Spencer Aaron Fernandes  
**USN:** 25MCAR0123  
**Application Name:** MedAlert  
**Platform:** Android  
**Programming Language:** Kotlin  
**UI Technology:** XML  
**IDE:** Android Studio

---

# 1. 🎯 Aim

To develop an Android application named **MedAlert** that allows users to enter and manage medicine information, display scheduled medicines, view complete medicine details, and generate a notification when a medicine reminder is enabled.

The application demonstrates important Android development concepts such as:

- Activities
- Fragments
- Explicit Intents
- XML layouts
- EditText
- TextView
- Button
- RadioButton
- RadioGroup
- CheckBox
- Fragment Result API
- Android Notifications
- Runtime Notification Permission
- Activity Lifecycle
- Fragment Lifecycle
- Logcat

---

# 2. 📖 Introduction

**MedAlert** is a simple medicine reminder Android application designed to help users record and keep track of their medicines.

The application provides a simple interface where the user can enter:

- Medicine Name
- Dosage
- Medicine Type
- Frequency
- Reminder Requirement

After saving the medicine, the information is displayed on the main dashboard.

If the reminder option is enabled, the application generates an Android notification.

The user can click the saved medicine to open a separate **Medicine Details Activity**, where all the medicine information is displayed.

---

# 3. 🧠 Concept / Technology Used

## 3.1 Android Activity

An Activity represents a screen in an Android application.

The MedAlert application contains two main Activities:

    MainActivity
    MedicineDetailsActivity

### MainActivity

`MainActivity` is the main screen of the application.

It contains:

- MedAlert application title
- Student details
- USN
- Medicine Dashboard
- Scheduled medicine information
- Add Medicine button

### MedicineDetailsActivity

`MedicineDetailsActivity` displays the complete information of a selected medicine.

It receives the medicine information from `MainActivity` using an explicit Intent.

---

# 4. 🧩 Fragment

The application uses a Fragment called:

    AddMedicineFragment

The Fragment contains the medicine input form.

The user can enter:

    Medicine Name
    Dosage

The user can select a medicine type:

    Tablet
    Syrup
    Capsule

The user can select the medicine frequency:

    Once Daily
    Twice Daily
    Three Times Daily

The user can also enable:

    ☑ 🔔 Reminder Required

After clicking **SAVE MEDICINE**, the Fragment sends the entered information back to `MainActivity` using the Fragment Result API.

---

# 5. 🔗 Explicit Intent

An explicit Intent is used to navigate from:

    MainActivity
          ↓
    MedicineDetailsActivity

The following medicine information is passed through the Intent:

    name
    dosage
    type
    frequency
    reminder

Example:

    val intent = Intent(
        this,
        MedicineDetailsActivity::class.java
    )

    intent.putExtra("name", medicineName)
    intent.putExtra("dosage", medicineDosage)
    intent.putExtra("type", medicineType)
    intent.putExtra("frequency", medicineFrequency)
    intent.putExtra("reminder", medicineReminder)

    startActivity(intent)

---

# 6. 🔄 Fragment Result API

The **Fragment Result API** is used to transfer medicine information from `AddMedicineFragment` to `MainActivity`.

The data flow is:

    ┌─────────────────────────┐
    │  AddMedicineFragment    │
    │                         │
    │  Medicine Name          │
    │  Dosage                 │
    │  Type                   │
    │  Frequency              │
    │  Reminder               │
    └────────────┬────────────┘
                 │
                 │ Fragment Result
                 ↓
    ┌─────────────────────────┐
    │      MainActivity       │
    │                         │
    │  Medicine Dashboard     │
    └─────────────────────────┘

The following values are transferred:

    name
    dosage
    type
    frequency
    reminder

---

# 7. 🔔 Android Notification

MedAlert supports Android notifications when the user enables the reminder option.

The user selects:

    ☑ 🔔 Reminder Required

When the medicine is saved, a notification is generated.

The notification implementation uses:

    NotificationChannel
    NotificationCompat
    NotificationManagerCompat

Example notification:

    ┌─────────────────────────────────────┐
    │ 💊 MedAlert                         │
    │                                     │
    │ Medicine Reminder Created           │
    │                                     │
    │ Paracetamol - 500 mg reminder       │
    │ has been created.                   │
    └─────────────────────────────────────┘

For Android 13 and higher, the application requests notification permission.

The permission used is:

    <uses-permission
        android:name="android.permission.POST_NOTIFICATIONS" />

---

# 8. 🏥 Application Scenario

The application is designed for a user who needs to keep track of medicines.

For example, a user may need to take **Paracetamol 500 mg twice daily**.

The user opens MedAlert and enters:

    Medicine Name : Paracetamol
    Dosage        : 500 mg
    Medicine Type : Tablet
    Frequency     : Twice Daily
    Reminder      : Enabled

After clicking **SAVE MEDICINE**:

1. The entered information is validated.
2. The medicine information is sent to `MainActivity`.
3. The medicine is displayed on the Dashboard.
4. A notification is generated if the reminder is enabled.
5. The user can click the saved medicine.
6. `MedicineDetailsActivity` opens.
7. Complete medicine details are displayed.
8. The user can return to the Dashboard.

---

# 9. 🧱 Component Wireframe

The overall application component structure is:

    ┌───────────────────┐
    │   MedAlert App    │
    └─────────┬─────────┘
              │
              ↓
    ┌───────────────────┐
    │   MainActivity    │
    │    Dashboard      │
    └─────────┬─────────┘
              │
       Click Add Medicine
              │
              ↓
    ┌──────────────────────────┐
    │  AddMedicineFragment     │
    │                          │
    │  Medicine Name           │
    │  Dosage                  │
    │  Medicine Type           │
    │  Frequency               │
    │  Reminder                │
    └────────────┬─────────────┘
                 │
            Save Medicine
                 │
                 ↓
    ┌───────────────────┐
    │   MainActivity    │
    │    Dashboard      │
    └─────────┬─────────┘
              │
       Click Medicine
              │
              ↓
    ┌─────────────────────────────┐
    │ MedicineDetailsActivity     │
    │                             │
    │ Medicine Name               │
    │ Dosage                      │
    │ Medicine Type               │
    │ Frequency                   │
    │ Reminder Status             │
    └─────────────────────────────┘

---

# 10. 🎨 UI Wireframes

## 10.1 Dashboard Wireframe

    ┌─────────────────────────────────────────────┐
    │                                             │
    │  💊 MedAlert                                │
    │  Medicine Reminder                          │
    │                                             │
    │  ┌───────────────────────────────────────┐  │
    │  │ STUDENT DETAILS                       │  │
    │  │                                       │  │
    │  │ Spencer Fernandes                     │  │
    │  │ USN: 25MCAR0123                       │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    │  Medicine Dashboard                        │
    │  Your scheduled medicines                  │
    │                                             │
    │  ┌───────────────────────────────────────┐  │
    │  │ 💊 Paracetamol                        │  │
    │  │                                       │  │
    │  │ Dosage: 500 mg                        │  │
    │  │ Type: Tablet                          │  │
    │  │ Frequency: Twice Daily                │  │
    │  │ Reminder: Enabled 🔔                  │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    │  ┌───────────────────────────────────────┐  │
    │  │          ＋ ADD MEDICINE              │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    └─────────────────────────────────────────────┘

---

## 10.2 Add Medicine Wireframe

    ┌─────────────────────────────────────────────┐
    │                                             │
    │  💊 Add Medicine                            │
    │  Create a new medicine schedule            │
    │                                             │
    │  Medicine Name                              │
    │  ┌───────────────────────────────────────┐  │
    │  │ e.g. Paracetamol                      │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    │  Dosage                                     │
    │  ┌───────────────────────────────────────┐  │
    │  │ e.g. 500 mg                           │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    │  Medicine Type                              │
    │                                             │
    │  ○ Tablet                                  │
    │  ○ Syrup                                   │
    │  ○ Capsule                                 │
    │                                             │
    │  Frequency                                  │
    │                                             │
    │  ○ Once Daily                              │
    │  ○ Twice Daily                             │
    │  ○ Three Times Daily                       │
    │                                             │
    │  ☑ 🔔 Reminder Required                    │
    │                                             │
    │  ┌───────────────────────────────────────┐  │
    │  │          💾 SAVE MEDICINE             │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    └─────────────────────────────────────────────┘

---

## 10.3 Medicine Details Wireframe

    ┌─────────────────────────────────────────────┐
    │                                             │
    │           💊 Medicine Details               │
    │                                             │
    │               Paracetamol                   │
    │                                             │
    │  Dosage: 500 mg                             │
    │                                             │
    │  Medicine Type: Tablet                      │
    │                                             │
    │  Frequency: Twice Daily                     │
    │                                             │
    │  🔔 Reminder: Enabled                       │
    │                                             │
    │  ┌───────────────────────────────────────┐  │
    │  │       ← BACK TO DASHBOARD             │  │
    │  └───────────────────────────────────────┘  │
    │                                             │
    └─────────────────────────────────────────────┘

---

# 11. 🔄 Application Flow

    START
      │
      ↓
    ┌────────────────────┐
    │    MainActivity    │
    │     Dashboard      │
    └─────────┬──────────┘
              │
       Click Add Medicine
              │
              ↓
    ┌────────────────────────┐
    │ AddMedicineFragment    │
    │                        │
    │ Enter Medicine Name    │
    │ Enter Dosage           │
    │ Select Medicine Type   │
    │ Select Frequency       │
    │ Enable Reminder        │
    └───────────┬────────────┘
                │
               Save
                │
                ↓
    ┌─────────────────────┐
    │  Input Validation   │
    └──────────┬──────────┘
               │
          Valid Data?
           /       \
         No         Yes
         │           │
         ↓           ↓
    Show Error   Fragment Result
                       │
                       ↓
             ┌───────────────────┐
             │   MainActivity    │
             │    Dashboard      │
             └─────────┬─────────┘
                       │
              ┌────────┴────────┐
              │                 │
              ↓                 ↓
      Reminder Enabled?   Click Medicine
              │                 │
              ↓                 ↓
        Notification    MedicineDetailsActivity
                                  │
                                  ↓
                         Display Information
                                  │
                                  ↓
                          Back to Dashboard

---

# 12. 📁 Project Structure

The complete project structure is:

    MedAlert/
    │
    ├── app/
    │   │
    │   ├── build.gradle.kts
    │   │
    │   └── src/
    │       │
    │       └── main/
    │           │
    │           ├── java/
    │           │   │
    │           │   └── com/
    │           │       │
    │           │       └── example/
    │           │           │
    │           │           └── medalert/
    │           │               │
    │           │               ├── MainActivity.kt
    │           │               │
    │           │               ├── AddMedicineFragment.kt
    │           │               │
    │           │               └── MedicineDetailsActivity.kt
    │           │
    │           ├── res/
    │           │   │
    │           │   ├── drawable/
    │           │   │   │
    │           │   │   ├── backgroundone
    │           │   │   ├── backgroundtwo
    │           │   │   ├── button_background.xml
    │           │   │   └── card_background.xml
    │           │   │
    │           │   ├── layout/
    │           │   │   │
    │           │   │   ├── activity_main.xml
    │           │   │   ├── fragment_add_medicine.xml
    │           │   │   └── activity_medicine_details.xml
    │           │   │
    │           │   ├── values/
    │           │   │   │
    │           │   │   ├── colors.xml
    │           │   │   ├── strings.xml
    │           │   │   └── themes.xml
    │           │   │
    │           │   └── xml/
    │           │
    │           └── AndroidManifest.xml
    │
    ├── screenshots/
    │   ├── 1.png
    │   ├── 2.png
    │   └── 3.png
    │
    ├── build.gradle.kts
    ├── settings.gradle.kts
    └── README.md

---

# 13. 📄 File Description

| File | Purpose |
|---|---|
| `MainActivity.kt` | Controls the main dashboard and navigation |
| `AddMedicineFragment.kt` | Handles medicine input and notification creation |
| `MedicineDetailsActivity.kt` | Displays complete medicine information |
| `activity_main.xml` | Main Dashboard UI |
| `fragment_add_medicine.xml` | Add Medicine form UI |
| `activity_medicine_details.xml` | Medicine Details UI |
| `AndroidManifest.xml` | Declares application components and permissions |
| `colors.xml` | Stores application colors |
| `strings.xml` | Stores application strings |
| `themes.xml` | Defines application theme |
| `button_background.xml` | Custom button background |
| `card_background.xml` | Custom card background |
| `backgroundone` | Application background resource |
| `backgroundtwo` | Application background resource |

---

# 14. 🖥️ Application Output

The MedAlert application contains the following main outputs:

### Dashboard

The Dashboard displays the student information and scheduled medicine.

### Add Medicine Screen

The user can enter and configure a medicine.

### Medicine Details

The user can view complete medicine information.

### Notification

A notification is generated when the reminder is enabled.

## 📸 Application Output Screenshot

![Application Output](screenshots/1.png)

---

# 15. 🧪 Test Case 1 – Student Details

## Objective

To verify that the application correctly displays the student's name and USN on the Dashboard.

## Test Data

    Name: Spencer Fernandes
    USN: 25MCAR0123

## Steps

1. Launch the MedAlert application.
2. Open the Dashboard.
3. Locate the Student Details section.
4. Verify the student's name.
5. Verify the USN.

## Expected Result

The application should display:

    Spencer Fernandes
    USN: 25MCAR0123

## Screenshot

![Test Case 1 – Student Details](screenshots/1.png)

---

# 16. 🧪 Test Case 2 – Add Medicine

## Objective

To verify that the user can successfully enter and save medicine information.

## Test Data

    Medicine Name: Paracetamol
    Dosage: 500 mg
    Medicine Type: Tablet
    Frequency: Twice Daily
    Reminder: Enabled

## Steps

1. Launch MedAlert.
2. Click **ADD MEDICINE**.
3. Enter `Paracetamol`.
4. Enter `500 mg`.
5. Select `Tablet`.
6. Select `Twice Daily`.
7. Enable **Reminder Required**.
8. Click **SAVE MEDICINE**.

## Expected Result

The medicine should be successfully saved and displayed on the Dashboard.

Example:

    💊 Paracetamol

    Dosage: 500 mg
    Type: Tablet
    Frequency: Twice Daily
    Reminder: Enabled 🔔

## Screenshot

![Test Case 2 – Add Medicine](screenshots/2.png)

---

# 17. 🧪 Test Case 3 – Medicine Details and Notification

## Objective

To verify that the medicine details are correctly displayed in `MedicineDetailsActivity` and that the notification is generated when the reminder is enabled.

## Steps

1. Add a medicine.
2. Enable the reminder.
3. Save the medicine.
4. Check the Android notification panel.
5. Click the medicine from the Dashboard.
6. Verify the Medicine Details screen.
7. Verify all displayed information.
8. Click **BACK TO DASHBOARD**.

## Expected Result

The application should:

- Create the medicine successfully.
- Generate a notification when the reminder is enabled.
- Open the Medicine Details Activity.
- Display the medicine name.
- Display the dosage.
- Display the medicine type.
- Display the frequency.
- Display the reminder status.
- Allow the user to return to the Dashboard.

## Screenshot

![Test Case 3 – Medicine Details and Notification](screenshots/3.png)

---

# 18. 🔔 Notification Output

When the reminder is enabled, the application creates a notification.

Example:

    ┌─────────────────────────────────────┐
    │ 💊 MedAlert                         │
    │                                     │
    │ Medicine Reminder Created           │
    │                                     │
    │ Paracetamol - 500 mg reminder       │
    │ has been created.                   │
    └─────────────────────────────────────┘

---

# 19. 🔐 Notification Permission

For Android 13 and higher, notification permission is required.

The permission is declared in `AndroidManifest.xml`:

    <uses-permission
        android:name="android.permission.POST_NOTIFICATIONS" />

The application checks whether notification permission has been granted.

If permission has not been granted, the application requests it from the user.

---

# 20. ✅ Input Validation

The application validates user input before saving the medicine.

## Medicine Name

If the medicine name is empty, an error message is displayed asking the user to enter the medicine name.

## Dosage

If the dosage is empty, an error message is displayed asking the user to enter the dosage.

## Medicine Type

The user must select one of:

- Tablet
- Syrup
- Capsule

## Frequency

The user must select one of:

- Once Daily
- Twice Daily
- Three Times Daily

---

# 21. 🔄 Activity Lifecycle

The application demonstrates the Activity lifecycle using logging.

The following lifecycle methods are used in `MainActivity`:

    onCreate()
    onStart()
    onResume()
    onPause()
    onStop()
    onDestroy()

The lifecycle methods help demonstrate how an Activity behaves during different stages of its execution.

---

# 22. 🔄 Fragment Lifecycle

`AddMedicineFragment` also demonstrates Fragment lifecycle methods.

Examples include:

    onCreate()
    onViewCreated()
    onStart()
    onResume()
    onPause()
    onStop()
    onDestroyView()

The lifecycle information can be observed using Android Studio Logcat.

The application uses the Logcat tag:

    MedAlert

Example Logcat output:

    MedAlert: MainActivity: onCreate()
    MedAlert: MainActivity: onStart()
    MedAlert: MainActivity: onResume()

    MedAlert: AddMedicineFragment: onCreate()
    MedAlert: AddMedicineFragment: onViewCreated()
    MedAlert: AddMedicineFragment: onStart()
    MedAlert: AddMedicineFragment: onResume()

---

# 23. 🎨 UI Design

The MedAlert application uses a clean medical-themed interface.

The UI contains:

- Medical-themed background
- Blue-themed components
- White cards
- Custom rounded buttons
- Student details section
- Medicine Dashboard
- Medicine input form
- Medicine details screen
- Notification support

The custom UI resources are stored inside:

    app/src/main/res/drawable/

Important resources include:

    backgroundone
    backgroundtwo
    button_background.xml
    card_background.xml

---

# 24. ▶️ How to Run the Application

### Step 1

Open the **MedAlert** project in Android Studio.

### Step 2

Wait for Gradle synchronization to complete.

### Step 3

Connect an Android device or start an Android Emulator.

### Step 4

Click the **Run ▶** button in Android Studio.

### Step 5

The MedAlert Dashboard will appear.

### Step 6

Click:

    ＋ ADD MEDICINE

### Step 7

Enter the medicine name and dosage.

### Step 8

Select the medicine type.

### Step 9

Select the frequency.

### Step 10

Enable the reminder if required.

### Step 11

Click:

    💾 SAVE MEDICINE

### Step 12

Verify that the medicine appears on the Dashboard.

### Step 13

If the reminder is enabled, check the Android notification panel.

### Step 14

Click the medicine information to open the Medicine Details screen.

### Step 15

Click:

    ← BACK TO DASHBOARD

to return to the Dashboard.

---

# 25. 📸 Screenshot Directory

The project contains a `screenshots` folder at the root level.



The `screenshots` folder must be located at the same level as `README.md`.

Correct structure:

    MedAlert/
    │
    ├── app/
    │
    ├── screenshots/
    │
    ├── build.gradle.kts
    ├── settings.gradle.kts
    └── README.md

The screenshots are referenced in this README using relative paths.

---

# 26. 📚 Learning Outcomes

After completing this Lab Test, the following Android concepts are demonstrated:

- Android Activity development
- Fragment development
- XML-based UI development
- Explicit Intent navigation
- Passing data using Intent Extras
- Fragment Result API
- EditText
- TextView
- Button
- CheckBox
- RadioButton
- RadioGroup
- Input validation
- Android Notification
- Notification Channel
- Runtime Notification Permission
- Activity Lifecycle
- Fragment Lifecycle
- Logcat debugging
- Android project structure
- GitHub project documentation

---

# 27. 🎯 Key Functionalities

The main functionalities of MedAlert are:

    ✓ Display student information
    ✓ Add medicine
    ✓ Enter medicine dosage
    ✓ Select medicine type
    ✓ Select medicine frequency
    ✓ Enable/disable reminder
    ✓ Validate user input
    ✓ Display scheduled medicine
    ✓ View medicine details
    ✓ Navigate between Activities
    ✓ Use Fragment Result API
    ✓ Generate Android notification
    ✓ Request notification permission
    ✓ Demonstrate Activity lifecycle
    ✓ Demonstrate Fragment lifecycle

---

# 28. 🧪 Testing Summary

| Test Case | Description | Expected Result |
|---|---|---|
| Test Case 1 | Verify Student Name and USN | Student details are displayed correctly |
| Test Case 2 | Add and save medicine | Medicine appears on Dashboard |
| Test Case 3 | View medicine details and notification | Details are displayed and notification is generated |

---

# 29. 📊 Application Architecture

The basic architecture of the application can be represented as:

    ┌──────────────────────────────┐
    │          USER                │
    └──────────────┬───────────────┘
                   │
                   ↓
    ┌──────────────────────────────┐
    │       MainActivity           │
    │                              │
    │       Dashboard              │
    └──────────────┬───────────────┘
                   │
                   ↓
    ┌──────────────────────────────┐
    │    AddMedicineFragment       │
    │                              │
    │    Medicine Input Form       │
    └──────────────┬───────────────┘
                   │
                   ↓
    ┌──────────────────────────────┐
    │     Fragment Result API      │
    └──────────────┬───────────────┘
                   │
                   ↓
    ┌──────────────────────────────┐
    │       MainActivity           │
    │                              │
    │    Display Medicine          │
    └──────────────┬───────────────┘
                   │
          ┌────────┴─────────┐
          │                  │
          ↓                  ↓
    ┌──────────────┐   ┌─────────────────────┐
    │ Notification │   │ MedicineDetails     │
    │   System     │   │ Activity            │
    └──────────────┘   └─────────────────────┘

---

# 30. 🔁 Data Flow

The medicine information flows through the application as follows:

    User enters medicine details
                │
                ↓
    AddMedicineFragment
                │
                ↓
        Input Validation
                │
                ↓
       Fragment Result API
                │
                ↓
         MainActivity
                │
                ├──────────────→ Dashboard
                │
                ├──────────────→ Notification
                │
                ↓
    MedicineDetailsActivity
                │
                ↓
       Display Full Details

---

# 31. 📝 Important Android Components

## EditText

Used to accept text input from the user.

Examples:

- Medicine Name
- Dosage

## RadioButton

Used when the user needs to select one option from a group.

Examples:

- Tablet
- Syrup
- Capsule

## CheckBox

Used to enable or disable the medicine reminder.

## Button

Used to perform actions such as:

- Add Medicine
- Save Medicine
- Back to Dashboard

## TextView

Used to display information such as:

- Student Name
- USN
- Medicine Name
- Dosage
- Frequency
- Reminder Status

---

# 32. 📱 Application Screens

The application contains three main user interface screens.

### Screen 1 – Dashboard

Displays:

- Application title
- Student details
- USN
- Medicine information
- Add Medicine button

### Screen 2 – Add Medicine

Allows the user to:

- Enter medicine name
- Enter dosage
- Select medicine type
- Select frequency
- Enable reminder
- Save medicine

### Screen 3 – Medicine Details

Displays:

- Medicine name
- Dosage
- Medicine type
- Frequency
- Reminder status
- Back to Dashboard button

---

# 33. 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Android Studio | Development Environment |
| Kotlin | Programming Language |
| XML | User Interface Design |
| Android SDK | Application Development |
| Android Activities | Screen Navigation |
| Android Fragments | Modular UI |
| Explicit Intent | Activity Navigation and Data Transfer |
| Fragment Result API | Fragment-to-Activity Data Transfer |
| Notification API | Medicine Reminder Notification |
| Logcat | Debugging and Lifecycle Monitoring |

---

# 34. 📌 Advantages of MedAlert

- Simple and user-friendly interface
- Easy medicine entry
- Clear medicine information
- Reminder notification support
- Separate medicine details screen
- Demonstrates Android Activity navigation
- Demonstrates Fragment usage
- Demonstrates data transfer between components
- Provides input validation
- Uses a medical-themed UI

---

# 35. 🚀 Future Enhancements

The current application can be extended with additional functionality in the future.

Possible enhancements include:

- Multiple medicine records
- Persistent local database using Room
- Actual scheduled notifications
- Medicine reminder time selection
- Medicine history
- Medicine search
- Edit and delete medicine
- Medicine categories
- Notification scheduling
- Dark mode
- User authentication
- Cloud synchronization
- Medicine expiry tracking

---

# 36. 🏁 Result

The **MedAlert Medicine Reminder Android Application** was successfully developed using **Kotlin and XML in Android Studio**.

The application successfully demonstrates the use of:

- Activities
- Fragments
- Explicit Intents
- Fragment Result API
- XML UI components
- Input validation
- Android Notifications
- Notification Permissions
- Activity Lifecycle
- Fragment Lifecycle
- Logcat

The application allows the user to enter medicine information, save it to the Dashboard, enable a reminder notification, and view complete medicine details through a separate Activity.

Thus, the required objectives of **Lab Test 1** were successfully achieved.

---

# 👨‍💻 Student Details

| Field | Details |
|---|---|
| **Name** | Spencer Fernandes |
| **USN** | 25MCAR0123 |
| **Application** | MedAlert |
| **Lab Test** | Lab Test 1 |
| **Technology** | Android / Kotlin / XML |
| **IDE** | Android Studio |

---

# 📌 Project Summary

**MedAlert** is an Android-based medicine reminder application developed as part of **Lab Test 1**.

The project focuses on demonstrating fundamental Android application development concepts through a practical medicine reminder scenario.

The complete application flow is:

    💊 MedAlert
          │
          ↓
    Medicine Dashboard
          │
          ↓
    Add Medicine Fragment
          │
          ↓
    Enter Medicine Information
          │
          ↓
    Save Medicine
          │
          ├──────────────→ Dashboard
          │
          ├──────────────→ Notification
          │
          ↓
    Medicine Details Activity
          │
          ↓
    View Complete Medicine Details
          │
          ↓
    Back to Dashboard

---

