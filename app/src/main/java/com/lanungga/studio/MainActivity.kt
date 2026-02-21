package com.lanungga.studio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.lanungga.studio.ui.theme.*
import kotlinx.coroutines.delay
import java.net.URLEncoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LanunggaStudioTheme {
                var currentScreen by remember { mutableStateOf("welcome") }
                var selectedTab by remember { mutableIntStateOf(0) }

                LaunchedEffect(Unit) {
                    delay(3000)
                    currentScreen = "main"
                }

                Crossfade(targetState = currentScreen, animationSpec = tween(800), label = "") { screen ->
                    when (screen) {
                        "welcome" -> WelcomeScreen()
                        "main" -> MainContainer(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainContainer(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Scaffold(
        containerColor = Color(0xFFF8FAFC),
        bottomBar = {
            Column(modifier = Modifier.background(Color.White).navigationBarsPadding()) {
                AdmobBanner()
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                NavigationBar(containerColor = Color.White, tonalElevation = 0.dp) {
                    val tabs = listOf("Home", "Solutions", "Academy", "Profile")
                    val icons = listOf(Icons.Default.Home, Icons.Default.List, Icons.Default.School, Icons.Default.Person)
                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { onTabSelected(index) },
                            label = { Text(title, fontSize = 10.sp) },
                            icon = { Icon(icons[index], contentDescription = title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = LanunggaBlue,
                                selectedTextColor = LanunggaBlue,
                                unselectedIconColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> HomeScreen(
                    onGoToSolutions = { onTabSelected(1) },
                    onGoToAcademy = { onTabSelected(2) }
                )
                1 -> SolutionsScreen(onBack = { onTabSelected(0) })
                2 -> AcademyScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(onGoToSolutions: () -> Unit, onGoToAcademy: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.logo_lanungga_studio), contentDescription = null, modifier = Modifier.height(30.dp))
        Spacer(modifier = Modifier.height(24.dp))

        // Hero Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(modifier = Modifier.background(Brush.linearGradient(listOf(LanunggaBlue, Color(0xFF1E40AF))))) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Partner Digital Branding & Marketing", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("Apakah Anda Mengalami Ini?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(16.dp))

        // PAIN POINTS (Copywriting Dokter Tetap Sesuai Permintaan)
        PainPointItem("Info Klinik / Praktek Mandiri Anda Sulit ditemukan di Google?")
        PainPointItem("Belum punya Website Resmi Klinik atau Website Personal Branding Dokter?")
        PainPointItem("Mau naik level punya Aplikasi Android sendiri?")
        PainPointItem("Mahasiswa Kedokteran/Dokter yang bingung di era digital yang berubah cepat?")
        PainPointItem("Ingin bisa survive dan bertumbuh di era digital?")

        Spacer(modifier = Modifier.height(32.dp))

        // CTA 1: ACADEMY
        Card(
            modifier = Modifier.fillMaxWidth().clickable { onGoToAcademy() },
            colors = CardDefaults.cardColors(containerColor = LanunggaLime.copy(alpha = 0.1f)),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, LanunggaLime.copy(alpha = 0.3f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.School, contentDescription = null, tint = Color(0xFF166534))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Ikut kelas kami di Academy", fontWeight = FontWeight.Bold, color = Color(0xFF166534))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // CTA 2: SOLUTIONS
        Card(
            modifier = Modifier.fillMaxWidth().clickable { onGoToSolutions() },
            colors = CardDefaults.cardColors(containerColor = LanunggaBlue.copy(alpha = 0.1f)),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, LanunggaBlue.copy(alpha = 0.3f))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Lightbulb, contentDescription = null, tint = LanunggaBlue)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Tenang, Kami Punya Solusinya", fontWeight = FontWeight.Bold, color = LanunggaBlue)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // CTA 3: WHATSAPP (FIXED PRE-FILLED MESSAGE)
        Text("Bingung mau mulai dari mana?", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val message = """
                    Halo Lanungga Studio, saya butuh bantuan digital marketing.
                    
                    Nama : 
                    Nama Klinik / Praktek Pribadi : 
                    Kota : 
                """.trimIndent()
                val encodedMsg = URLEncoder.encode(message, "UTF-8")
                val url = "https://wa.me/628122544052?text=$encodedMsg"
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.Chat, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Hubungi Kami ", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PainPointItem(text: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = LanunggaBlue.copy(alpha = 0.6f), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontSize = 15.sp, color = Color.DarkGray)
    }
}

@Composable
fun SolutionsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp)) {
        Text("Our Solutions", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = LanunggaBlue)
        Spacer(modifier = Modifier.height(32.dp))
        SolutionItem("Google Business Profile", "Optimasi agar Klinik Anda muncul di Google Maps.", Icons.Default.LocationOn)
        SolutionItem("Custom Website & App", "Website dan Aplikasi Android Native eksklusif brand Anda.", Icons.Default.Devices)
        SolutionItem("Digital Branding Strategy", "Pengelolaan konten untuk membangun trust pasien.", Icons.Default.VerifiedUser)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val message = """
                    Halo Lanungga Studio, saya tertarik dengan Solusi Digital Anda.
                    
                    Nama : 
                    Nama Klinik / Praktek Pribadi : 
                    Kota : 
                """.trimIndent()
                val encodedMsg = URLEncoder.encode(message, "UTF-8")
                val url = "https://wa.me/628122544052?text=$encodedMsg"
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
        ) {
            Icon(Icons.Default.Chat, null); Spacer(modifier = Modifier.width(12.dp)); Text("Konsultasi Sekarang", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) { Text("Kembali", color = Color.Gray) }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun SolutionItem(title: String, desc: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).background(Color.White, RoundedCornerShape(16.dp)).padding(16.dp)) {
        Surface(modifier = Modifier.size(48.dp), shape = RoundedCornerShape(12.dp), color = LanunggaBlue.copy(alpha = 0.1f)) {
            Box(contentAlignment = Alignment.Center) { Icon(icon, null, tint = LanunggaBlue, modifier = Modifier.size(24.dp)) }
        }
        Spacer(modifier = Modifier.width(16.dp)); Column { Text(title, fontWeight = FontWeight.Bold); Text(desc, fontSize = 12.sp, color = Color.Gray) }
    }
}

@Composable
fun AcademyScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
        Text("Lanungga Academy\nMateri Edukasi Medis Digital Segera Hadir", textAlign = TextAlign.Center)
    }
}

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo_lanungga_studio), null, modifier = Modifier.size(120.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Text("Your Partner in Digital Branding", fontWeight = FontWeight.Bold, color = LanunggaBlue)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """
                Lanungga Studio adalah agensi pemasaran digital yang berdedikasi untuk memberdayakan (empowering) dokter dan klinik melalui teknologi mutakhir. Kami percaya bahwa setiap praktik medis berhak memiliki reputasi digital yang kuat dan profesional.

                Sebagai partner strategis Anda, kami menghadirkan solusi teknologi yang mudah diakses dan efektif—mulai dari optimasi Google Business Profile hingga pembuatan Website dan Aplikasi Android Native custom dengan brand eksklusif Anda. Kami hadir untuk memastikan pertumbuhan praktik Anda tetap melesat tanpa Anda perlu pusing mengelola tim IT internal.

                Dengan pendekatan yang menggabungkan presisi teknologi medis dan strategi pemasaran digital, Lanungga Studio siap mendukung Anda menjadi pemimpin di era kesehatan digital.
            """.trimIndent(),
            textAlign = TextAlign.Justify,
            color = Color.Black,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                val message = "Halo Lanungga Studio, saya butuh informasi layanan."
                val url = "https://wa.me/628122544052?text=${URLEncoder.encode(message, "UTF-8")}"
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
            shape = RoundedCornerShape(16.dp)
        ) { Text("Hubungi Kami", fontWeight = FontWeight.Bold) }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun AdmobBanner() {
    AndroidView(modifier = Modifier.fillMaxWidth().height(50.dp), factory = { context ->
        AdView(context).apply { setAdSize(AdSize.BANNER); adUnitId = "ca-app-pub-3940256099942544/6300978111"; loadAd(AdRequest.Builder().build()) }
    })
}

@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Lanungga Studio
            Image(
                painter = painterResource(id = R.drawable.logo_lanungga_studio),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ANIMASI LOADING BIRU & HIJAU
            LinearProgressIndicator(
                modifier = Modifier
                    .width(140.dp)
                    .height(6.dp)
                    .clip(RoundedCornerShape(10.dp)), // Membuat ujung loading jadi bulat/curved
                color = LanunggaBlue,             // Warna bar yang berjalan (Biru)
                trackColor = LanunggaLime.copy(alpha = 0.3f) // Warna dasar/background (Hijau transparan)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Initializing Ecosystem...",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
        }
    }
}