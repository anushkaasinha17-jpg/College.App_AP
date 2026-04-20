package com.example.collegeandroidapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.collegeandroidapp.ui.theme.*

data class FacultyMember(
    val name: String,
    val designation: String,
    val department: String,
    val email: String,
    val expertise: String
)

val facultyList = listOf(
    FacultyMember("Dr. Rajesh Sharma", "Professor & Head", "Science & Technology",
        "r.sharma@vupune.ac.in", "Artificial Intelligence, Machine Learning"),
    FacultyMember("Dr. Priya Desai", "Associate Professor", "Commerce & Management",
        "p.desai@vupune.ac.in", "Business Analytics, Finance"),
    FacultyMember("Prof. Amit Kulkarni", "Assistant Professor", "Architecture",
        "a.kulkarni@vupune.ac.in", "Sustainable Design, Urban Planning"),
    FacultyMember("Dr. Sneha Joshi", "Professor", "Humanities & Social Sciences",
        "s.joshi@vupune.ac.in", "Psychology, Counselling"),
    FacultyMember("Prof. Rakesh Patil", "Associate Professor", "Law & Governance",
        "r.patil@vupune.ac.in", "Constitutional Law, Human Rights"),
    FacultyMember("Dr. Meera Nair", "Assistant Professor", "Art & Design",
        "m.nair@vupune.ac.in", "UI/UX Design, Visual Communication"),
    FacultyMember("Dr. Vikas Tiwari", "Professor", "Pharmacy",
        "v.tiwari@vupune.ac.in", "Pharmaceutical Chemistry, Drug Delivery"),
    FacultyMember("Prof. Kavita Rao", "Associate Professor", "Media & Communication",
        "k.rao@vupune.ac.in", "Journalism, Digital Media"),
    FacultyMember("Dr. Suresh Bhat", "Professor", "Science & Technology",
        "s.bhat@vupune.ac.in", "Data Science, Cloud Computing"),
    FacultyMember("Dr. Anjali Mehta", "Assistant Professor", "Commerce & Management",
        "a.mehta@vupune.ac.in", "Marketing, Consumer Behaviour"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedDeptFilter by remember { mutableStateOf("All") }

    val deptFilters = listOf("All", "Science & Technology", "Commerce & Management",
        "Architecture", "Humanities & Social Sciences", "Law & Governance",
        "Art & Design", "Pharmacy", "Media & Communication")

    val filtered = facultyList.filter {
        (selectedDeptFilter == "All" || it.department == selectedDeptFilter) &&
        (searchQuery.isBlank() ||
            it.name.contains(searchQuery, true) ||
            it.department.contains(searchQuery, true) ||
            it.expertise.contains(searchQuery, true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_BlueDark, VU_Blue)))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                    }
                    Spacer(Modifier.width(4.dp))
                    Column {
                        Text("Faculty Directory", style = MaterialTheme.typography.titleLarge,
                            color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Our Expert Educators", style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(0.75f))
                    }
                }
                Spacer(Modifier.height(14.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search by name, department, expertise...") },
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

        // Department filter chips
        ScrollableTabRow(
            selectedTabIndex = deptFilters.indexOf(selectedDeptFilter),
            containerColor = MaterialTheme.colorScheme.surface,
            edgePadding = 12.dp,
            indicator = {}
        ) {
            deptFilters.forEach { dept ->
                val selected = dept == selectedDeptFilter
                FilterChip(
                    selected = selected,
                    onClick = { selectedDeptFilter = dept },
                    label = { Text(dept.substringBefore(" &").take(12), style = MaterialTheme.typography.labelSmall) },
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = VU_Blue,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text("${filtered.size} faculty members",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(bottom = 4.dp))
            }
            items(filtered) { faculty ->
                FacultyCard(faculty)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyCard(faculty: FacultyMember) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(listOf(VU_Blue, VU_BlueLight))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    faculty.name.first().toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(faculty.name, style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold)
                Text(faculty.designation, style = MaterialTheme.typography.bodySmall,
                    color = VU_Blue, fontWeight = FontWeight.Medium)
                Text(faculty.department, style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline)
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = VU_Gold, modifier = Modifier.size(12.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(faculty.expertise, style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.7f), maxLines = 1)
                }
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Email, null, tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(12.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(faculty.email, style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline)
                }
            }
        }
    }
}
