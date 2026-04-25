# 📱 Deepfake Detection Android App

An AI-powered Android application that detects whether an image is **real or fake (deepfake)** directly on-device using an integrated machine learning model.
This app performs **real-time inference without any backend**, making it fast, private, and efficient.

---

## 🚀 Features

* 📸 Capture or select image using CameraX
* 🧠 On-device Deepfake Detection (no internet required)
* 📊 Displays prediction result with confidence score
* ⚡ Fast inference using integrated ML model
* 🔁 Re-analyze images instantly
* 🎨 Modern UI built with Jetpack Compose

---

## 🛠️ Tech Stack

### 📱 Android Development

* Kotlin
* Jetpack Compose
* CameraX
* Navigation Component

### 🤖 Machine Learning (On-Device)

* Integrated Deepfake Detection Model
* TensorFlow Lite / Custom Model (update if needed)

---

## 📂 Project Structure

```
app/
├── ui/
│   ├── screens/
│   ├── theme/
├── model/              # ML model + processing logic
├── navigation/
├── MainActivity.kt
```

---

## ⚙️ How It Works

1. User captures or selects an image
2. Image is processed inside the app
3. ML model predicts:

   * ✅ Real
   * ❌ Fake (Deepfake)
4. Result is displayed with confidence score

---

## 📸 Screenshots

*Add your app screenshots here*

---

## 🔐 Advantages of On-Device AI

* 🚫 No server required
* 🔒 Better privacy (data stays on device)
* ⚡ Faster predictions
* 📶 Works offline

---

## 🎯 Future Enhancements

* 🌐 Backend integration (FastAPI)
* ☁️ Cloud-based model updates
* 🎥 Video deepfake detection
* 📍 Metadata analysis (EXIF, geotagging)

---

## 🤝 Contributing

Feel free to fork this repository and submit pull requests.

---

## 📜 License

This project is licensed under the MIT License.

---

## 👩‍💻 Developer

**Pranjali Shukla**

---

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub!
