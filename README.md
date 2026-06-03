# 🎓 Lanungga Studio App

Portfolio + LMS + WhatsApp lead platform untuk dokter praktek pribadi dan klinik. **Native Android app** dengan Jetpack Compose.

> **Status**: MVP ready, approaching Play Store submission (60% ready)

---

## 🎯 Visi

**Proof. Learn. Trust. Order.**

Etalase digital untuk dokter & klinik Indonesia. Tiga fungsi utama:
1. **Portfolio** — Studi kasus & bukti kerja digital agency
2. **LMS** — Kelas online marketing & bisnis untuk dokter
3. **Contact** — Lead capture via WhatsApp (628122544052)

---

## ✨ Features

### 🏠 Home Screen
- Hero banner dengan positioning jelas
- CTA buttons (Portfolio, LMS, WhatsApp)
- Bullet points & info cards untuk edukasi user
- Gradient design (Lanungga Blue → Dark Blue)

### 📁 Portfolio
- Showcase 4+ project case studies (klinik, personal branding, app, SEO)
- Project detail screen dengan impact metrics
- Project tags & teknologi yang digunakan
- Direct WhatsApp button dari detail

### 🎓 LMS (Learning Management System)
- Course list dengan kategori
- Course detail screen dengan lessons
- Video player (ExoPlayer)
- Lesson progression tracking (in-memory)

### 💬 Contact
- One-click WhatsApp integration
- Pre-filled message template
- Service highlights & benefit cards

### 🎨 Design System
- Material Design 3 (Material You)
- Custom color palette (Lanungga Blue, Lime, White)
- Responsive layout (all screen sizes)
- Dark mode ready
- Accessibility-first (semantics, contrast)

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|------------|
| **Language** | Kotlin 1.9+ |
| **UI Framework** | Jetpack Compose (Material 3) |
| **Navigation** | Compose Navigation |
| **Image Loading** | Coil Compose |
| **Video Player** | ExoPlayer (Media3) |
| **Local Storage** | (Room ready, currently in-memory) |
| **Build System** | Gradle 8.x |
| **Min SDK** | 24 (Android 7.0) |
| **Target SDK** | 35 (Android 15) |
| **Compile SDK** | 35 |

---

## 📦 Project Structure

```
app/
├── src/main/
│   ├── java/com/lanungga/studio/
│   │   ├── MainActivity.kt                    # Entry point
│   │   ├── data/
│   │   │   ├── model/
│   │   │   │   ├── Project.kt                 # Portfolio project model
│   │   │   │   └── Course.kt                  # LMS course model
│   │   │   └── repository/
│   │   │       ├── PortfolioRepository.kt     # Dummy portfolio data
│   │   │       └── CourseRepository.kt        # Dummy LMS data
│   │   └── ui/
│   │       ├── components/                    # Reusable UI components
│   │       │   ├── AppButtons.kt
│   │       │   ├── AppCards.kt
│   │       │   ├── AppImage.kt
│   │       │   └── AppSection.kt
│   │       ├── navigation/
│   │       │   ├── AppNavGraph.kt             # Bottom nav + routing
│   │       │   └── AppDestinations.kt         # Route definitions
│   │       ├── screens/
│   │       │   ├── home/
│   │       │   ├── portfolio/
│   │       │   ├── lms/
│   │       │   └── contact/
│   │       └── theme/
│   │           ├── Color.kt
│   │           ├── Theme.kt
│   │           ├── Type.kt
│   │           ├── Shape.kt
│   │           └── Dimens.kt
│   └── res/
│       ├── drawable/                         # Icons, logos
│       ├── mipmap-*/                          # App icons (all densities)
│       └── values/
│           ├── strings.xml
│           └── colors.xml
├── build.gradle.kts                           # App-level build config
└── proguard-rules.pro                         # Code obfuscation rules
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Koala or later
- JDK 17+
- Android SDK 35+
- Minimum 4GB RAM

### Setup

```bash
# Clone repo
git clone https://github.com/yanuarimansantosa/LanunggaStudioApp.git
cd LanunggaStudioApp

# Open in Android Studio
# File > Open > (select project folder)

# Install dependencies
# Android Studio akan auto-download via Gradle

# Run on emulator atau device
# Click "Run" button atau Ctrl+F5
```

### Build APK

```bash
# Debug APK (for testing)
./gradlew assembleDebug

# Release APK (for Play Store)
# Requires keystore — set gradle.properties:
# LANUNGGA_UPLOAD_STORE_FILE=path/to/keystore.jks
# LANUNGGA_UPLOAD_STORE_PASSWORD=password
# LANUNGGA_UPLOAD_KEY_ALIAS=alias
# LANUNGGA_UPLOAD_KEY_PASSWORD=password
./gradlew assembleRelease
```

---

## 📊 Current Status

### ✅ Completed (60%)

- [x] Splash screen & app startup
- [x] Navigation structure (bottom nav + deep linking)
- [x] All 5 main screens (Home, Portfolio, LMS, Contact, Details)
- [x] UI components & design system
- [x] WhatsApp integration (contact screen)
- [x] App icons & logos
- [x] Release build config & ProGuard
- [x] Responsive layout (all screen sizes)

### ⏳ TODO Before Launch (40%)

- [ ] Privacy Policy (URL required for Play Store)
- [ ] Play Store listing assets (screenshots, icon, description)
- [ ] Release keystore setup & signing
- [ ] Content Rating questionnaire
- [ ] Real LMS course content (optional for MVP)
- [ ] Backend API for dynamic data (optional for MVP)
- [ ] Analytics (Firebase)
- [ ] Crash reporting (Sentry/Firebase)
- [ ] Comprehensive testing (unit + instrumentation)

---

## 🎬 Screens Overview

### Home Screen
- Welcome banner + positioning
- Feature cards (Portfolio, LMS)
- Why choose us (bullet points)
- Quick actions + CTA

### Portfolio Screen
- Grid/list of 4+ projects
- Filter by category/tags
- Live URL preview (kalau ada)

### Portfolio Detail
- Project thumbnail
- Problem → Solution → Results format
- Impact metrics (40% increase, etc)
- Contact CTA

### LMS Screen
- Course cards with cover image
- Course count & difficulty
- Direct course enrollment tap

### Course Detail
- Course cover & title
- Lesson list with duration
- Lesson tap → video player

### Lesson Player
- ExoPlayer video playback
- Full-screen & adaptive bitrate
- Lesson progress (basic)

### Contact Screen
- WhatsApp info + benefit
- One-tap message button
- Pre-filled message template

---

## 🔒 Privacy & Permissions

| Permission | Why |
|-----------|-----|
| `INTERNET` | Load images, WhatsApp link |
| `ACCESS_NETWORK_STATE` | Check connectivity |

No permissions for camera, location, contacts, or microphone (minimal footprint).

---

## 📝 Configuration

### App Name & Package
- **App Name**: Lanungga Studio
- **Package**: com.lanungga.studio
- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 35 (Android 15)

### Colors
- **Primary**: Lanungga Blue (#4A86E8)
- **Primary Dark**: Lanungga Blue Dark (#2C5AA0)
- **Accent**: Lanungga Lime (#43D692)
- **WhatsApp Green**: #25D366 (for contact CTA)

### Proguard
- Code obfuscation enabled (`minifyEnabled = true`)
- Resource shrinking enabled (`shrinkResources = true`)
- Keeps Compose, Coil, and Material3 public APIs

---

## 🧪 Testing

Currently no automated tests. To add:

```bash
# Unit tests
./gradlew test

# Instrumentation tests (on device/emulator)
./gradlew connectedAndroidTest
```

---

## 📱 Play Store Roadmap

### Phase 1: Pre-Launch (Next 1-2 weeks)
1. Create Privacy Policy (host on lanungga.com)
2. Prepare 5+ screenshots (portrait orientation)
3. Generate release keystore
4. Sign APK/AAB
5. Create Play Console app entry

### Phase 2: Launch (Week 3)
1. Fill app listing (description, features, category)
2. Fill content rating
3. Upload APK/AAB + screenshots
4. Submit for review

### Phase 3: Post-Launch
1. Monitor crash reports
2. Gather user feedback
3. Plan features v1.1+ (real LMS, backend, analytics)

---

## 💡 Future Enhancements

- [ ] **Backend Integration** — Firestore atau CMS untuk portfolio & LMS
- [ ] **Authentication** — Google Sign-In untuk LMS enrollment
- [ ] **Payment** — In-app billing untuk kursus premium
- [ ] **Analytics** — Firebase Analytics untuk user behavior
- [ ] **Push Notifications** — Course reminder & updates
- [ ] **Offline Mode** — Download lessons untuk offline viewing
- [ ] **Sharing** — Share projects & courses di social media

---

## 📞 Contact

**Founder**: Yanuar (Lanungga Studio)  
**WhatsApp**: [+62 812-2544-052](https://wa.me/628122544052)  
**Website**: [lanungga.studio](https://lanungga.studio)  
**Email**: yanuar.tht@gmail.com

---

## 📄 License

Private project. All rights reserved © 2026 Lanungga Studio.

---

**Last Updated**: June 3, 2026  
**App Version**: 1.0  
**Status**: Ready for Play Store submission review