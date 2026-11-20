package com.suthankan.beforetap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suthankan.beforetap.domain.DemoFixture

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        val preferences = getPreferences(MODE_PRIVATE)
        setContent {
            var hasOnboarded by rememberSaveable {
                mutableStateOf(preferences.getBoolean("has_onboarded", false))
            }
            MaterialTheme(colorScheme = lightColorScheme(primary = androidx.compose.ui.graphics.Color(0xFF006B5E))) {
                if (hasOnboarded) {
                    BeforeTapApp()
                } else {
                    Onboarding {
                        preferences.edit().putBoolean("has_onboarded", true).apply()
                        hasOnboarded = true
                    }
                }
            }
        }
    }
}

private enum class CheckRoute { HOME, SOURCE, PASTE, DEMO }

@Composable
private fun BeforeTapApp() {
    var destination by rememberSaveable { mutableIntStateOf(0) }
    var route by rememberSaveable { mutableStateOf(CheckRoute.HOME) }
    var pasteOrigin by rememberSaveable { mutableStateOf(CheckRoute.HOME) }
    val goBack: () -> Unit = {
        route = when (route) {
            CheckRoute.DEMO -> CheckRoute.PASTE
            CheckRoute.PASTE -> pasteOrigin
            else -> CheckRoute.HOME
        }
    }
    val openPaste: () -> Unit = {
        pasteOrigin = route
        route = CheckRoute.PASTE
    }
    BackHandler(enabled = route != CheckRoute.HOME, onBack = goBack)

    Scaffold(
        containerColor = Color(0xFFF3F8F4),
        bottomBar = {
            if (route == CheckRoute.HOME) {
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding().padding(horizontal = 24.dp)
                        .padding(bottom = 12.dp).clip(RoundedCornerShape(29.dp)),
                    containerColor = Color(0xFFE3EDE8),
                    windowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    val items = listOf("Check", "History", "Settings")
                    val icons = listOf(R.drawable.tab_check, R.drawable.tab_history, R.drawable.tab_settings)
                    items.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = destination == index,
                            onClick = { destination = index },
                            icon = { CheckArtwork(icons[index], 20) },
                            label = { Text(title) },
                            colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFC8E9DE))
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when {
                destination == 1 -> Placeholder("No saved checks", "History is not implemented.")
                destination == 2 -> Placeholder("Privacy settings", "Online checks and history are not enabled.")
                route == CheckRoute.SOURCE -> ChooseSource(onPaste = openPaste, onBack = goBack)
                route == CheckRoute.PASTE -> ReviewLink(onDemo = { route = CheckRoute.DEMO }, onBack = goBack)
                route == CheckRoute.DEMO -> DemoResult(onBack = goBack)
                else -> CheckHome(onPaste = openPaste, onChooseSource = { route = CheckRoute.SOURCE })
            }
        }
    }
}

@Composable
private fun DemoResult(onBack: () -> Unit) {
    val result = DemoFixture.suspicious
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("DEMO — NO LIVE SCAN", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
        Text("Suspicious", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(result.displayHost)
        result.evidence.forEach { evidence ->
            Card {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(evidence.title, fontWeight = FontWeight.Bold)
                    Text(evidence.explanation)
                    Text("Source: ${evidence.source}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Text(result.limitations.joinToString("\n"), color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("Verify independently through the organization's official app or website.")
        TextButton(onClick = onBack) { Text("Back to check") }
    }
}

@Composable
private fun Placeholder(title: String, description: String) {
    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(description, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
