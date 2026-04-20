package com.example.collegeandroidapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
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

data class Department(
    val name: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color,
    val programmes: List<String>
)

val departments = listOf(
    Department(
        "Science & Technology", Icons.Filled.Science, VU_Blue,
        listOf("B.Tech (AI & DS)", "B.Tech (AI & ML)", "B.Tech (AI)", "B.Tech (Computer Engineering)", "M.Tech", "Ph.D")
    ),
    Department(
        "Commerce & Management", Icons.Filled.Business, Color(0xFF1B5E20),
        listOf("BBA (Hons)", "BBA (International Business)", "BBA (Hospitality & Tourism)", "BCA (AI)", "MBA")
    ),
    Department(
        "Art & Design", Icons.Filled.Palette, Color(0xFF6A1B9A),
        listOf("B.Des (Product Design)", "B.Des (UI/UX)", "BA Animation & Multimedia", "B.Fashion Technology", "MA Animation")
    ),
    Department(
        "Architecture", Icons.Filled.Apartment, Color(0xFF004D40),
        listOf("B.Arch", "BA Interior Design & Decoration", "MA Interior Design")
    ),
    Department(
        "Law & Governance", Icons.Filled.Gavel, Color(0xFF783200),
        listOf("BBA-LLB", "LLB 3 Years", "LLM", "Master's in Governance & Public Policy")
    ),
    Department(
        "Humanities & Social Sciences", Icons.Filled.Groups, Color(0xFF880E4F),
        listOf("BA Economics", "BA Music (Hons)", "BA Prakrit & Jain Studies", "Advanced PG Diploma in Counselling")
    ),
    Department(
        "Pharmacy", Icons.Filled.LocalPharmacy, Color(0xFF0D47A1),
        listOf("B.Pharm", "D.Pharm", "M.Pharm (Quality Assurance)", "M.Pharm (Pharmaceutics)")
    ),
    Department(
        "Media & Communication", Icons.Filled.Mic, Color(0xFF33691E),
        listOf("BA Journalism & Mass Communication", "MA Journalism & Mass Communication")
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavController) {
    var selectedDept by remember { mutableStateOf<Department?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(listOf(VU_BlueDark, VU_Blue))
                )
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                Text(
                    "Programmes",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Explore 100+ UG, PG & PhD Programmes",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.75f)
                )
                Spacer(Modifier.height(14.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search programmes...", style = MaterialTheme.typography.bodySmall) },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.White.copy(0.7f)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VU_Gold,
                        unfocusedBorderColor = Color.White.copy(0.4f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = VU_Gold,
                        focusedPlaceholderColor = Color.White.copy(0.5f),
                        unfocusedPlaceholderColor = Color.White.copy(0.5f)
                    ),
                    singleLine = true
                )
            }
        }

        if (selectedDept == null) {
            // Departments grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                val filtered = if (searchQuery.isBlank()) departments
                else departments.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                    it.programmes.any { p -> p.contains(searchQuery, ignoreCase = true) }
                }

                items(filtered) { dept ->
                    DepartmentCard(dept) { selectedDept = dept }
                }
            }
        } else {
            // Programme list for selected dept
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { selectedDept = null }) {
                        Icon(Icons.Filled.ArrowBack, "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                    Text(
                        selectedDept!!.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = selectedDept!!.color
                    )
                }
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(selectedDept!!.programmes) { programme ->
                        ProgrammeCard(programme, selectedDept!!.color)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentCard(dept: Department, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(dept.color.copy(alpha = 0.12f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(dept.icon, contentDescription = null, tint = dept.color, modifier = Modifier.size(26.dp))
            }
            Column {
                Text(
                    dept.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
                Text(
                    "${dept.programmes.size} programmes",
                    style = MaterialTheme.typography.labelSmall,
                    color = dept.color.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgrammeCard(name: String, accentColor: Color) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(accentColor, RoundedCornerShape(50))
            )
            Spacer(Modifier.width(12.dp))
            Text(
                name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
