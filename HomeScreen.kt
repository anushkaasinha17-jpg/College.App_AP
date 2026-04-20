package com.example.collegeandroidapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.collegeandroidapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ── Hero Banner ──────────────────────────────────────────────────────
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(VU_BlueDark, VU_Blue, VU_BlueLight),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        )
                    )
            ) {
                // Decorative circles
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.White.copy(alpha = 0.05f),
                        radius = 300f,
                        center = Offset(size.width * 0.85f, size.height * 0.2f)
                    )
                    drawCircle(
                        color = Color(0xFFFFAA00).copy(alpha = 0.15f),
                        radius = 200f,
                        center = Offset(size.width * 0.1f, size.height * 0.9f)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Top bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Good Morning! 👋",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Text(
                                "Welcome to VU",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.15f), CircleShape)
                        ) {
                            Icon(
                                Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }

                    // University name
                    Column {
                        Text(
                            "Vishwakarma",
                            style = MaterialTheme.typography.headlineMedium,
                            color = VU_Gold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "University, Pune",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = null,
                                tint = VU_GoldLight,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                " Kondhwa Budruk, Pune - 411 048",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }

        // ── Stats Strip ──────────────────────────────────────────────────────
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatChip("10,000+", "Students")
                    VerticalDivider()
                    StatChip("100+", "Programmes")
                    VerticalDivider()
                    StatChip("50+", "Nations")
                    VerticalDivider()
                    StatChip("500+", "Faculty")
                }
            }
        }

        // ── Announcement Banner ──────────────────────────────────────────────
        item {
            AnnouncementBanner()
        }

        // ── Quick Actions ────────────────────────────────────────────────────
        item {
            Spacer(Modifier.height(20.dp))
            Text(
                "Quick Access",
                modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(12.dp))
        }

        item {
            val quickActions = listOf(
                Triple(Icons.Filled.MenuBook,     "Courses",     "courses"),
                Triple(Icons.Filled.Person,       "Faculty",     "faculty"),
                Triple(Icons.Filled.LocalLibrary, "Library",     "library"),
                Triple(Icons.Filled.BarChart,     "Attendance",  "attendance"),
                Triple(Icons.Filled.Notifications,"Notices",     "notices"),
                Triple(Icons.Filled.Map,          "Campus Map",  "campus_map")
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(quickActions) { (icon, label, route) ->
                    QuickActionCard(icon, label) { navController.navigate(route) }
                }
            }
        }

        // ── News & Happenings ────────────────────────────────────────────────
        item {
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Latest Happenings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { navController.navigate("events") }) {
                    Text("See All", color = MaterialTheme.colorScheme.primary)
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        items(newsItems) { news ->
            NewsCard(news)
        }

        // ── Placement Companies ──────────────────────────────────────────────
        item {
            Spacer(Modifier.height(24.dp))
            Text(
                "Top Recruiters",
                modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(12.dp))
            PlacementChipsRow()
            Spacer(Modifier.height(24.dp))
        }
    }
}

// ── Sub-composables ───────────────────────────────────────────────────────────

@Composable
fun StatChip(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = VU_Blue
        )
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(36.dp)
            .width(1.dp)
            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    )
}

@Composable
fun AnnouncementBanner() {
    var currentIndex by remember { mutableStateOf(0) }
    val announcements = listOf(
        "📢 Admissions Open for 2026-27 – Apply Now!",
        "📅 International Conference ICAIML-AE 2026 – Register Today",
        "📧 Backup your VU Email IDs – 2017-20 Batches"
    )

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            currentIndex = (currentIndex + 1) % announcements.size
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Campaign,
                contentDescription = null,
                tint = VU_Gold,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(10.dp))
            AnimatedContent(
                targetState = currentIndex,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) { idx ->
                Text(
                    announcements[idx],
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.size(width = 90.dp, height = 90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

data class NewsItem(val title: String, val category: String, val date: String)

val newsItems = listOf(
    NewsItem("3-Day Hands-on Workshop & Award Ceremony", "Happenings", "Jan 10, 2026"),
    NewsItem("Site Visit to Patil Plaza, Pune", "Happenings", "Jan 8, 2026"),
    NewsItem("National Mathematics & Statistics Day", "Events", "Dec 20, 2025"),
    NewsItem("5-Day AI Bootcamp", "Events", "Dec 15, 2025"),
    NewsItem("Digital Transformation of MSMEs Initiative", "Media", "Apr 2026")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(news: NewsItem) {
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = when (news.category) {
                            "Happenings" -> VU_Blue.copy(alpha = 0.12f)
                            "Events"     -> VU_Gold.copy(alpha = 0.15f)
                            else         -> MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (news.category) {
                        "Happenings" -> Icons.Filled.Star
                        "Events"     -> Icons.Filled.Event
                        else         -> Icons.Filled.Article
                    },
                    contentDescription = null,
                    tint = when (news.category) {
                        "Happenings" -> VU_Blue
                        "Events"     -> VU_Gold
                        else         -> MaterialTheme.colorScheme.primary
                    },
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            news.category,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    modifier = Modifier.height(22.dp)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    news.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    news.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun PlacementChipsRow() {
    val companies = listOf(
        "Accenture", "Bosch", "Tech Mahindra", "HDFC Bank",
        "Jio", "Kotak", "Michelin", "Vanderlande", "ZS Associates"
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(companies) { company ->
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            ) {
                Text(
                    company,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
