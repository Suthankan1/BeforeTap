package com.suthankan.beforetap

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

private val pageColor = Color(0xFFF3F8F4)
private val ink = Color(0xFF182C25)
private val secondaryInk = Color(0xFF5D7067)
private val brand = Color(0xFF006B5E)
private val brandContainer = Color(0xFFDAF5E8)

@Composable
internal fun Onboarding(onComplete: () -> Unit) {
    var showPrivacy by rememberSaveable { mutableStateOf(false) }
    BackHandler(enabled = showPrivacy) { showPrivacy = false }

    if (showPrivacy) {
        OnboardingPage(
            title = "Your messages stay yours.",
            subtitle = "BeforeTap puts your privacy in control, right from the first check.",
            action = "Continue",
            onAction = onComplete
        ) {
            Surface(color = brandContainer, shape = RoundedCornerShape(27.dp)) {
                Column(
                    Modifier.fillMaxWidth().padding(19.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Artwork(R.drawable.onboarding_lock, 34)
                        Text("Private by design", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("Drafts stay on this device. BeforeTap uploads nothing in this demo.", fontSize = 15.sp, lineHeight = 18.sp)
                }
            }
            PromiseCard(
                "Online checks are optional",
                "BeforeTap will ask before sending a URL."
            )
            PromiseCard(
                "No hidden tracking",
                "This demo keeps no check history."
            )
        }
    } else {
        OnboardingPage(
            title = "A pause can protect you.",
            subtitle = "Check before you click, pay or share information.",
            action = "Get started",
            onAction = { showPrivacy = true }
        ) {
            Surface(color = brandContainer, shape = RoundedCornerShape(27.dp)) {
                Column(
                    Modifier.fillMaxWidth().padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(11.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Artwork(R.drawable.onboarding_shield, 46)
                        Column {
                            Text("BeforeTap", fontSize = 31.sp, lineHeight = 36.sp, fontWeight = FontWeight.Bold)
                            Text("Pause. Check. Act.", color = brand, fontSize = 15.sp, lineHeight = 18.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                    Text("Understand suspicious links, messages and QR codes without guessing.", fontSize = 16.sp, lineHeight = 20.sp)
                }
            }
            PromiseCard(
                "Know the warning signs",
                "Clear explanations, not mysterious scores."
            )
            PromiseCard(
                "Keep control",
                "No automatic opening or payment."
            )
        }
    }
}

@Composable
private fun OnboardingPage(
    title: String,
    subtitle: String,
    action: String,
    onAction: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(color = pageColor, contentColor = ink, modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(start = 24.dp, top = 37.dp, end = 24.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                Modifier.weight(1f).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                ProvideTextStyle(TextStyle(letterSpacing = 0.sp)) {
                    Text("BEFORETAP", color = brand, fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.Medium)
                    Text(
                        title,
                        modifier = Modifier.semantics { heading() },
                        fontSize = 33.sp,
                        lineHeight = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(subtitle, color = secondaryInk, fontSize = 14.sp, lineHeight = 17.sp)
                    content()
                }
            }
            Button(
                onClick = onAction,
                modifier = Modifier.fillMaxWidth().heightIn(min = 68.dp),
                colors = ButtonDefaults.buttonColors(containerColor = brand, contentColor = Color.White)
            ) {
                Text(action, fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun PromiseCard(title: String, subtitle: String) {
    Surface(color = Color.White, contentColor = ink, shape = RoundedCornerShape(25.dp)) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Artwork(R.drawable.onboarding_check, 20)
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(title, fontSize = 14.sp, lineHeight = 17.sp, fontWeight = FontWeight.Medium)
                Text(subtitle, color = secondaryInk, fontSize = 12.sp, lineHeight = 15.sp)
            }
        }
    }
}

@Composable
private fun Artwork(@DrawableRes resource: Int, size: Int) {
    Image(painterResource(resource), contentDescription = null, modifier = Modifier.size(size.dp))
}
