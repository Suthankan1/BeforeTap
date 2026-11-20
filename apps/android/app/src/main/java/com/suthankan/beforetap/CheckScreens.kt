package com.suthankan.beforetap

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suthankan.beforetap.domain.LocalWebLink

private val background = Color(0xFFF3F8F4)
private val ink = Color(0xFF182C25)
private val mutedInk = Color(0xFF5D7067)
private val brand = Color(0xFF006B5E)
private val mint = Color(0xFFD8F2E8)

@Composable
internal fun CheckHome(onPaste: () -> Unit, onChooseSource: () -> Unit) {
    Surface(color = background, contentColor = ink, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp, bottom = 12.dp)) {
            CheckContent(Modifier.weight(1f), "Check before you tap.",
                "A suspicious link or message? Understand the warning signs first."
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 44.dp, bottomStart = 44.dp, bottomEnd = 24.dp),
                    color = mint
                ) {
                    Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(11.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            CheckArtwork(R.drawable.check_hero_shield, 34)
                            Text("A little pause. A lot more clarity.", fontSize = 20.sp, lineHeight = 23.sp, fontWeight = FontWeight.Bold)
                        }
                        Text("Review a destination before you open it.", fontSize = 14.sp, color = mutedInk)
                    }
                }
                SourceRow("Paste a link", "Inspect the destination", R.drawable.check_link, onPaste)
                SourceRow("Import screenshot", "Screenshot review is planned", R.drawable.check_image)
                SourceRow("Scan a QR code", "QR scanning is planned", R.drawable.check_qr)
                Surface(shape = RoundedCornerShape(26.dp), color = Color(0xFFECE8F8)) {
                    Row(Modifier.fillMaxWidth().padding(18.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        CheckArtwork(R.drawable.check_tip, 24)
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("A SMART WAY TO CHECK", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6D588D))
                            Text("A familiar logo does not prove a link is genuine.", fontSize = 13.sp, lineHeight = 17.sp)
                        }
                    }
                }
            }
            Button(onClick = onChooseSource, modifier = Modifier.fillMaxWidth().heightIn(min = 68.dp)) {
                Text("Check something", fontSize = 16.sp)
            }
        }
    }
}

@Composable
internal fun ChooseSource(onPaste: () -> Unit, onBack: () -> Unit) {
    Surface(color = background, contentColor = ink, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = 24.dp)) {
            TextButton(onClick = onBack) { Text("Back") }
            CheckContent(Modifier.weight(1f), "What would you like to check?",
                "Choose the content you received. It will not open automatically."
            ) {
                SourceRow("Link or message", "Paste a web link", R.drawable.check_link, onPaste)
                SourceRow("Screenshot", "Screenshot review is planned", R.drawable.check_image)
                SourceRow("QR code", "QR scanning is planned", R.drawable.check_qr)
                Surface(color = mint, shape = RoundedCornerShape(26.dp)) {
                    Text(
                        "Nothing you share is opened automatically.",
                        modifier = Modifier.fillMaxWidth().padding(17.dp),
                        color = mutedInk, fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
internal fun ReviewLink(onDemo: () -> Unit, onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<LocalWebLink?>(null) }
    var showError by remember { mutableStateOf(false) }
    Surface(color = background, contentColor = ink, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = 24.dp)) {
            TextButton(onClick = onBack) { Text("Back") }
            CheckContent(Modifier.weight(1f), "Review a link",
                "Paste one complete HTTP or HTTPS link to see its destination."
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        result = null
                        showError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Paste a web link") },
                    isError = showError,
                    minLines = 2,
                    maxLines = 4
                )
                if (showError) {
                    Text("Enter one valid web link beginning with http:// or https://.", color = MaterialTheme.colorScheme.error)
                }
                Button(
                    onClick = {
                        result = LocalWebLink.parse(input)
                        showError = result == null
                    },
                    enabled = input.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Review destination") }
                result?.let { link ->
                    val port = link.port?.let { ":$it" }.orEmpty()
                    Text("Destination: ${link.host}$port", fontWeight = FontWeight.Bold)
                    if (link.hasUserInfo) Text("This link contains user information before its destination.")
                    Text("Local preview only. No reputation check was performed.")
                }
                TextButton(onClick = onDemo) { Text("Explore a demo result") }
            }
        }
    }
}

@Composable
private fun CheckContent(modifier: Modifier, title: String, subtitle: String, content: @Composable () -> Unit) {
    Column(
        modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(top = 37.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ProvideTextStyle(TextStyle(letterSpacing = 0.sp)) {
            Text("BEFORETAP", color = brand, fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.Medium)
            Text(title, Modifier.semantics { heading() }, fontSize = 33.sp, lineHeight = 38.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = mutedInk, fontSize = 14.sp, lineHeight = 17.sp)
            content()
        }
    }
}

@Composable
private fun SourceRow(title: String, detail: String, @DrawableRes mark: Int, onClick: (() -> Unit)? = null) {
    val shape = RoundedCornerShape(26.dp)
    val border = BorderStroke(1.dp, Color(0xFFEDF0EE))
    val row: @Composable () -> Unit = {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 17.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CheckArtwork(mark, 25)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(title, fontSize = 14.sp, lineHeight = 17.sp, fontWeight = FontWeight.Medium)
                Text(detail, color = mutedInk, fontSize = 12.sp, lineHeight = 15.sp)
            }
            if (onClick != null) Text("›", color = mutedInk, fontSize = 22.sp)
        }
    }
    if (onClick == null) {
        Surface(shape = shape, color = Color.White, border = border, content = row)
    } else {
        Surface(onClick = onClick, shape = shape, color = Color.White, border = border, content = row)
    }
}

@Composable
internal fun CheckArtwork(@DrawableRes mark: Int, size: Int) {
    Image(painterResource(mark), contentDescription = null, modifier = Modifier.size(size.dp))
}
