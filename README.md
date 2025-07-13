<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/f66d8e40-42af-42c2-be33-d9975d7db49f" alt="Quizora App Icon" width="80" height="80"/></td>
    <td><h1>Quizora - Android Quiz App</h1></td>
  </tr>
</table>

**Quizora** is a modern quiz application built using **Android (Kotlin + MVVM)** and a lightweight **PHP backend** to serve dynamic questions via a REST API.

---

## 🚀 Features

- Dynamic quiz questions loaded via API
- Score tracking and results display
- Clean, dark-themed UI with Material Design
- Simple PHP backend for serving MCQs from MySQL

---

## 📸 Screenshot

![screenshot_quizora](https://github.com/user-attachments/assets/b256cf73-39cb-42c0-9d4b-6b2949d3ad46)


---

## 📁 Folder Structure

```
Quizora/
├── app/ # Android App source code
├── server/ # PHP backend files
│ └── questionapi.php # Fetches quiz questions from MySQL DB
├── assets/ # App icon and screenshots
│ ├── app_icon.png
│ ├── screenshot1.png
│ └── screenshot2.png
├── build.gradle
└── README.md
```
---

## 📱 How to Run the Android App

1. Open the project in **Android Studio**
2. Update your IP in:
   ```kotlin
   // retrofit/RetrofitInstance.kt
   val baseurl = "http://192.168.xx.xxx/quizora/"
   ```
3. Run the app on emulator or real device (same Wi-Fi)

---

## 🌐 How to Run the PHP Backend

1. Install and start XAMPP
2. Place questionapi.php inside:
```
C:/xampp/htdocs/quizora/
```
3. Create a MySQL database:
DB Name: quizora_db
Table: math_quiz

```sql
CREATE TABLE math_quiz (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question TEXT NOT NULL,
  option1 VARCHAR(255),
  option2 VARCHAR(255),
  option3 VARCHAR(255),
  option4 VARCHAR(255),
  correct_option VARCHAR(255)
);
```
4. Insert sample data to test.

---

## 📡 How to Test the API
Open browser and hit:
```
http://localhost/quizora/questionapi.php
```
Expected Output (JSON):

```json
[
  {
    "question": "What is 2 + 2?",
    "option1": "3",
    "option2": "4",
    "option3": "5",
    "option4": "22",
    "correct_option": "4"
  }
]
```
If working, you're ready to run the app!

## ⚠️ Common Fixes
Issues	& Solutions :
App not loading questions -	Check IP address in RetrofitInstance.kt
NetworkOnMainThreadException - Ensure all API calls are inside coroutines
NullPointerException on LiveData - Add null safety using it?.let {}
API not found	- Check if XAMPP is running and PHP file is in correct folder

## 🎓 What I Learned
1. How to build a full-stack mobile app (Android + PHP)
2. Integrating Retrofit with Kotlin coroutines
3. Using MVVM + LiveData architecture
4. Designing a minimal yet functional quiz UI
5. Hosting API using XAMPP locally

## 🙋‍♂️ Want to Contribute?
Fork this repo
Create a feature branch (feature/your-feature)
Commit and push
Open a Pull Request
