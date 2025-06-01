# 🔥 IoT-Based Fire Detection and Extinguishing Robot

A smart robotic system that uses IoT, camera vision, and autonomous mobility to detect, approach, and extinguish fire. The robot also supports live video streaming, voice alerts, two-way audio communication, and real-time control through a mobile/web app hosted on Firebase.

## 🚀 Project Overview

This project aims to build a fire-fighting robot that enhances safety by:
- Detecting fire using ESP32-CAM or flame sensors
- Streaming live video to a mobile app
- Navigating towards the fire automatically or manually
- Spraying water using a servo-controlled pump
- Sending voice alerts via DFPlayer Mini
- Receiving environmental sound using a microphone
- Controlled securely over the internet using Firebase

## 🧩 System Components

| Component        | Description |
|------------------|-------------|
| ESP32-CAM        | Real-time video streaming and fire detection |
| ESP8266/ESP32    | Wi-Fi module for IoT connectivity |
| L298N Motor Driver | Controls 4-wheel robot mobility |
| DFPlayer Mini    | Plays pre-recorded voice alerts |
| Microphone       | Captures sound from the robot environment |
| Water Pump + Servo | Automatically extinguishes fire |
| Firebase         | Realtime database, authentication, and hosting |

## 📱 App Features

- 🔐 Firebase Authentication (Email/Password or Google Sign-in)
- 🎥 Live video stream from ESP32-CAM
- 🎮 Manual and semi-auto control of the robot
- 🔊 Voice feedback through speaker
- 🎙️ Mic streaming from robot to user
- 🌐 Hosted on Firebase with secure database connectivity

## 📐 Circuit Diagram

> (Insert your circuit diagram image or link here)

## 🖼️ Screenshots

> (Insert app UI screenshots and robot images)

## 🔧 How It Works

1. **Startup**: Robot connects to Wi-Fi and Firebase.
2. **Monitoring**: ESP32-CAM streams video; users view feed via app.
3. **Detection**: Fire detected through IR sensor or image processing.
4. **Action**: Robot moves towards the fire and activates the pump.
5. **Audio**: Voice alerts are played and microphone streams audio.
6. **Control**: All components are monitored and controlled remotely.

## 📦 Installation & Setup

### 🔌 Hardware
- ESP32-CAM
- ESP32 or NodeMCU
- L298N motor driver + 4 DC motors
- DFPlayer Mini + Speaker
- Microphone module
- Water pump + Servo motor

### 🧠 Software
- Arduino IDE (with ESP32 board support)
- Firebase project (Authentication + Realtime DB + Hosting)
- Android Studio (if using custom app)
- Web frontend (HTML/CSS/JS or React)

### 🔗 Firebase Configuration
Add your Firebase credentials to the ESP32 code:
```cpp
#define FIREBASE_AUTH "your_auth_token"
#define FIREBASE_HOST "your_project.firebaseio.com"
📂 Project Structure
bash
Copy
Edit
├── /hardware         # Circuit diagrams, images
├── /firmware         # Arduino code for ESP32, ESP32-CAM, etc.
├── /app              # Web or Android app source
├── /media            # Images, videos, and app screenshots
└── README.md         # Project documentation
💡 Future Enhancements
AI-powered flame detection via image processing

Obstacle avoidance and GPS-based navigation

Twilio/IFTTT integration for SMS/email alerts

Battery status monitoring

Integration with smart home systems
