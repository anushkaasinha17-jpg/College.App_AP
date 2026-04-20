package com.example.collegeandroidapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.collegeandroidapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(VU_BlueDark, VU_Blue),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Text("Explore More", style = MaterialTheme.typography.headlineSmall,
                        color = Color.White, fontWeight = FontWeight.Bold)
                    Text("All features at your fingertips", style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(0.75f))
                }
            }
        }

        item {
            Spacer(Modifier.height(20.dp))
            Text("Campus Services", modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(10.dp))
        }

        item {
            val services = listOf(
                Triple(Icons.Filled.Person,        "Faculty Directory",  "faculty"),
                Triple(Icons.Filled.LocalLibrary,  "Library",            "library"),
                Triple(Icons.Filled.BarChart,      "Attendance Tracker", "attendance"),
                Triple(Icons.Filled.Notifications, "Notices & Circulars","notices"),
                Triple(Icons.Filled.Map,           "Campus Map",         "campus_map"),
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                services.forEach { (icon, label, route) ->
                    MoreListTile(icon, label, route, navController)
                }
            }
        }

        item {
            Spacer(Modifier.height(24.dp))
            Text("Important Links", modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(10.dp))
        }

        item {
            val links = listOf(
                Triple(Icons.Filled.School,       "Academic Calendar",        ""),
                Triple(Icons.Filled.BeachAccess,  "Holiday List",             ""),
                Triple(Icons.Filled.Assignment,   "Standard Forms Download",  ""),
                Triple(Icons.Filled.EmojiEvents,  "Results",                  ""),
                Triple(Icons.Filled.Stars,        "NAAC Accreditation",       ""),
                Triple(Icons.Filled.BarChart,     "NIRF Rankings",            ""),
                Triple(Icons.Filled.Groups,       "Alumni Portal",            ""),
                Triple(Icons.Filled.Work,         "Careers & Placements",     ""),
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                links.forEach { (icon, label, _) ->
                    MoreListTile(icon, label, "", navController, isExternal = true)
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            // Contact card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Contact Us", style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(8.dp))
                    ContactRow(Icons.Filled.LocationOn, "Survey No. 2, 3, 4 Laxmi Nagar,\nKondhwa Budruk, Pune – 411 048")
                    Spacer(Modifier.height(6.dp))
                    ContactRow(Icons.Filled.Phone, "+91 9590300911 (Admissions)\n+91 8530164186 (Other)")
                    Spacer(Modifier.height(6.dp))
                    ContactRow(Icons.Filled.Email, "admissions@vupune.ac.in")
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = VU_Blue)
                        ) { Text("Call Now") }
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, VU_Blue)
                        ) { Text("Email Us") }
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreListTile(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    route: String,
    navController: NavController,
    isExternal: Boolean = false
) {
    Card(
        onClick = { if (route.isNotEmpty()) navController.navigate(route) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(VU_Blue.copy(0.1f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = VU_Blue, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(14.dp))
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                if (isExternal) Icons.Filled.OpenInNew else Icons.Filled.ChevronRight,
                null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(icon, null, tint = VU_Blue, modifier = Modifier.size(16.dp).padding(top = 2.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}
