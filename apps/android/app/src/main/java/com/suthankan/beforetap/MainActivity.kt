package com.suthankan.beforetap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suthankan.beforetap.domain.DemoFixture

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme(primary = androidx.compose.ui.graphics.Color(0xFF006B5E))) {
                BeforeTapApp()
            }
        }
    }
}

@Composable
private fun BeforeTapApp() {
    var destination by rememberSaveable { mutableIntStateOf(0) }
    var showDemo by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf("Check", "History", "Settings")
                val icons = listOf(Icons.Outlined.VerifiedUser, Icons.Outlined.History, Icons.Outlined.Settings)
                items.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = destination == index,
                        onClick = { destination = index; showDemo = false },
                        icon = { Icon(icons[index], contentDescription = null) },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when {
                destination == 0 && showDemo -> DemoResult(onBack = { showDemo = false })
                destination == 0 -> CheckHome(onDemo = { showDemo = true })
                destination == 1 -> Placeholder("No saved checks", "History is not implemented.")
                else -> Placeholder("Privacy settings", "Online checks and history are not enabled.")
            }
        }
    }
}

@Composable
private fun CheckHome(onDemo: () -> Unit) {
    var input by rememberSaveable { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("BEFORETAP", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Text("Check before you tap.", fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 36.sp)
        Text("A suspicious link or message? Understand the warning signs first.")
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Text("A little pause. A lot more clarity.", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(22.dp))
        }
        OutlinedTextField(
            value = input, onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Paste a URL or message") },
            minLines = 2
        )
        Text("Screenshot import — planned", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("QR scanning — planned", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Button(onClick = onDemo, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("Explore a demo result")
        }
        Text("DEMO ONLY — no check is performed and no URL is sent.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
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
