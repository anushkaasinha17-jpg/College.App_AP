package com.example.collegeandroidapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.collegeandroidapp.ui.theme.*

data class EventItem(
    val title: String,
    val category: String,
    val date: String,
    val description: String
)

val allEvents = listOf(
    EventItem("National Mathematics & World Statistics Day",
        "Events", "Dec 22, 2025", "Celebrating excellence in mathematics and statistics at VU."),
    EventItem("5-Day Bootcamp on Artificial Intelligence",
        "Events", "Dec 15-20, 2025", "Intensive AI training covering ML, NLP, and Computer Vision."),
    EventItem("Udaan of Happiness – Visit to Aasra Ashram",
        "Events", "Nov 30, 2025", "NSS volunteers visit old age home Aasra Ashram, Undri, Pune."),
    EventItem("3-Day Hands-on Workshop & Award Ceremony",
        "Happenings", "Jan 7–10, 2026", "Practical workshops followed by an awards ceremony."),
    EventItem("Site Visit to Patil Plaza, Pune",
        "Happenings", "Jan 8, 2026", "Architecture students visit Patil Plaza for real-world exposure."),
    EventItem("Site Visit to Pentagon, Pune-Satara Road",
        "Happenings", "Jan 6, 2026", "Structural analysis field visit for engineering students."),
    EventItem("ICAIML-AE 2026 – International Conference",
        "Media", "Apr 2026", "International Conference on AI & ML in Applied Engineering."),
    EventItem("India's Shiksha Shakti at GlobalImpact Forum 2026",
        "Media", "Mar 2026", "VU represented India at the prestigious GlobalImpact Forum."),
    EventItem("Launch of DV-Edge for MSME Digital Transformation",
        "Media", "Feb 2026", "Pioneering initiative for digital transformation of MSMEs."),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(navController: NavController) {
    val tabs = listOf("All", "Events", "Happenings", "Media")
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_Blue, VU_BlueLight)))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                Text("Events & News", style = MaterialTheme.typography.headlineSmall,
                    color = Color.White, fontWeight = FontWeight.Bold)
                Text("Stay updated with campus life", style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.75f))
            }
        }

        // Tab Row
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = VU_Gold,
                    height = 3.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        val filtered = if (selectedTab == 0) allEvents
        else allEvents.filter { it.category == tabs[selectedTab] }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filtered) { event ->
                EventCard(event)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCard(event: EventItem) {
    val (bgColor, iconRes) = when (event.category) {
        "Events"     -> Pair(VU_Gold.copy(0.12f),   Icons.Filled.Event)
        "Happenings" -> Pair(VU_Blue.copy(0.10f),    Icons.Filled.Star)
        else         -> Pair(Color(0xFF1B5E20).copy(0.10f), Icons.Filled.Article)
    }
    val accentColor = when (event.category) {
        "Events"     -> VU_Gold
        "Happenings" -> VU_Blue
        else         -> Color(0xFF2E7D32)
    }

    Card(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(bgColor, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(iconRes, null, tint = accentColor, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text(event.category, style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.height(24.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = accentColor.copy(0.1f),
                            labelColor = accentColor
                        ),
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = accentColor.copy(0.3f)
                        )
                    )
                    Text(
                        event.date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    event.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    event.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.65f),
                    maxLines = 2
                )
            }
        }
    }
}
