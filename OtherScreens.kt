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

// ═══════════════════════════════════════════════════════════════
//  LIBRARY SCREEN
// ═══════════════════════════════════════════════════════════════

data class BookItem(val title: String, val author: String, val category: String, val available: Boolean)

val libraryBooks = listOf(
    BookItem("Introduction to Algorithms", "Cormen et al.", "Computer Science", true),
    BookItem("Clean Code", "Robert C. Martin", "Software Engineering", false),
    BookItem("Design Patterns", "Gang of Four", "Software Engineering", true),
    BookItem("The Innovator's Dilemma", "Clayton Christensen", "Management", true),
    BookItem("Artificial Intelligence: A Modern Approach", "Russell & Norvig", "AI/ML", false),
    BookItem("Legal Aspects of Business", "Akhileshwar Pathak", "Law", true),
    BookItem("Pharmaceutical Analysis", "A.H. Beckett", "Pharmacy", true),
    BookItem("Mass Communication Theory", "McQuail", "Media Studies", false),
    BookItem("History of Architecture", "Banister Fletcher", "Architecture", true),
    BookItem("Principles of Economics", "N. Gregory Mankiw", "Economics", true),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var showAvailableOnly by remember { mutableStateOf(false) }

    val filtered = libraryBooks.filter {
        (if (showAvailableOnly) it.available else true) &&
        (searchQuery.isBlank() || it.title.contains(searchQuery, true) ||
         it.author.contains(searchQuery, true) || it.category.contains(searchQuery, true))
    }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_BlueDark, VU_Blue)))
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                    }
                    Column {
                        Text("Library", style = MaterialTheme.typography.titleLarge,
                            color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Search & Reserve Books", style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(0.75f))
                    }
                }
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search books, authors, subjects...") },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.White.copy(0.7f)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VU_Gold, unfocusedBorderColor = Color.White.copy(0.4f),
                        focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                        cursorColor = VU_Gold,
                        focusedPlaceholderColor = Color.White.copy(0.5f),
                        unfocusedPlaceholderColor = Color.White.copy(0.5f)
                    ),
                    singleLine = true
                )
            }
        }

        // Stats + filter row
        Surface(color = MaterialTheme.colorScheme.surface, shadowElevation = 2.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    LibraryStatChip("50,000+", "Books", Icons.Filled.MenuBook)
                    LibraryStatChip("24x7", "E-Library", Icons.Filled.Laptop)
                    LibraryStatChip("200+", "Journals", Icons.Filled.Article)
                }
                FilterChip(
                    selected = showAvailableOnly,
                    onClick = { showAvailableOnly = !showAvailableOnly },
                    label = { Text("Available", style = MaterialTheme.typography.labelSmall) },
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
            items(filtered) { book ->
                BookCard(book)
            }
        }
    }
}

@Composable
fun LibraryStatChip(value: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(icon, null, tint = VU_Blue, modifier = Modifier.size(14.dp))
        Column {
            Text(value, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = VU_Blue)
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(book: BookItem) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(VU_Blue.copy(0.1f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.MenuBook, null, tint = VU_Blue, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, maxLines = 2)
                Text(book.author, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                Text(book.category, style = MaterialTheme.typography.labelSmall, color = VU_Blue)
            }
            Spacer(Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(50),
                color = if (book.available) Color(0xFF1B5E20).copy(0.12f) else Color.Red.copy(0.1f)
            ) {
                Text(
                    if (book.available) "Available" else "Issued",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = if (book.available) Color(0xFF2E7D32) else Color(0xFFC62828)
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════
//  ATTENDANCE SCREEN
// ═══════════════════════════════════════════════════════════════

data class SubjectAttendance(val subject: String, val attended: Int, val total: Int)

val attendanceData = listOf(
    SubjectAttendance("Data Structures & Algorithms", 38, 42),
    SubjectAttendance("Machine Learning", 30, 40),
    SubjectAttendance("Computer Networks", 35, 38),
    SubjectAttendance("Database Management Systems", 28, 36),
    SubjectAttendance("Software Engineering", 22, 30),
    SubjectAttendance("Operating Systems", 40, 44),
)

@Composable
fun AttendanceScreen(navController: NavController) {
    val overallAttended = attendanceData.sumOf { it.attended }
    val overallTotal    = attendanceData.sumOf { it.total }
    val overallPct      = if (overallTotal > 0) (overallAttended * 100f / overallTotal) else 0f

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_BlueDark, VU_Blue)))
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                }
                Column {
                    Text("Attendance Tracker", style = MaterialTheme.typography.titleLarge,
                        color = Color.White, fontWeight = FontWeight.Bold)
                    Text("AY 2025-26 | Semester II", style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(0.75f))
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Overall card
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = VU_Blue),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Overall Attendance", style = MaterialTheme.typography.titleSmall,
                            color = Color.White.copy(0.8f))
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "${overallPct.toInt()}%",
                            style = MaterialTheme.typography.displayMedium,
                            color = if (overallPct >= 75f) VU_GoldLight else Color(0xFFFF6B6B),
                            fontWeight = FontWeight.ExtraBold
                        )
                        LinearProgressIndicator(
                            progress = { overallPct / 100f },
                            modifier = Modifier.fillMaxWidth().height(8.dp).padding(top = 8.dp),
                            color = if (overallPct >= 75f) VU_Gold else Color(0xFFFF6B6B),
                            trackColor = Color.White.copy(0.3f)
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("$overallAttended / $overallTotal classes attended",
                            style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.75f))
                        if (overallPct < 75f) {
                            Spacer(Modifier.height(4.dp))
                            Text("⚠️ Attendance below 75%! Please attend more classes.",
                                style = MaterialTheme.typography.labelSmall, color = Color(0xFFFFCDD2))
                        }
                    }
                }
            }

            item {
                Text("Subject-wise Breakdown",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp))
            }

            items(attendanceData) { subject ->
                AttendanceCard(subject)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceCard(data: SubjectAttendance) {
    val pct = if (data.total > 0) (data.attended * 100f / data.total) else 0f
    val statusColor = when {
        pct >= 85f -> Color(0xFF2E7D32)
        pct >= 75f -> VU_Gold
        else       -> Color(0xFFC62828)
    }

    Card(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(data.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                    maxLines = 2)
                Text(
                    "${pct.toInt()}%",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = statusColor
                )
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { pct / 100f },
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = statusColor,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text("${data.attended} / ${data.total} classes",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline)
        }
    }
}

// ═══════════════════════════════════════════════════════════════
//  NOTICES SCREEN
// ═══════════════════════════════════════════════════════════════

data class Notice(val title: String, val category: String, val date: String, val isImportant: Boolean = false)

val notices = listOf(
    Notice("Admissions Open for 2026-27 – Apply Now!", "Admissions", "Apr 1, 2026", true),
    Notice("Backup VU Email IDs – 2017-20 Batches", "General", "Mar 25, 2026", true),
    Notice("ICAIML-AE 2026 International Conference", "Academic", "Mar 20, 2026"),
    Notice("Examinations Time Table – Semester II", "Examinations", "Mar 15, 2026", true),
    Notice("PhD Research Grant Applications Open", "Research", "Mar 10, 2026"),
    Notice("Faculty Positions Available", "Careers", "Feb 28, 2026"),
    Notice("Holiday List 2026 Published", "General", "Jan 1, 2026"),
    Notice("Academic Calendar 2025-26 Released", "Academic", "Jul 1, 2025"),
    Notice("NAAC Accreditation Visit Schedule", "Accreditation", "Jun 15, 2025"),
    Notice("Student Grievance Portal Update", "Students", "Jun 1, 2025"),
)

@Composable
fun NoticesScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Admissions", "Academic", "Examinations", "General", "Research", "Students")

    val filtered = if (selectedCategory == "All") notices
    else notices.filter { it.category == selectedCategory }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_BlueDark, VU_Blue)))
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                }
                Column {
                    Text("Notices & Circulars", style = MaterialTheme.typography.titleLarge,
                        color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Stay informed with official announcements", style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(0.75f))
                }
            }
        }

        ScrollableTabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            containerColor = MaterialTheme.colorScheme.surface,
            edgePadding = 12.dp,
            indicator = {}
        ) {
            categories.forEach { cat ->
                FilterChip(
                    selected = cat == selectedCategory,
                    onClick = { selectedCategory = cat },
                    label = { Text(cat, style = MaterialTheme.typography.labelSmall) },
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
            items(filtered) { notice ->
                NoticeCard(notice)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeCard(notice: Notice) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (notice.isImportant)
                VU_Gold.copy(0.08f) else MaterialTheme.colorScheme.surface
        ),
        border = if (notice.isImportant)
            BorderStroke(1.dp, VU_Gold.copy(0.4f)) else null,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.Top) {
            Icon(
                if (notice.isImportant) Icons.Filled.PriorityHigh else Icons.Filled.Notifications,
                null,
                tint = if (notice.isImportant) VU_Gold else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(20.dp).padding(top = 2.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(notice.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = VU_Blue.copy(0.1f)
                    ) {
                        Text(notice.category,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = VU_Blue)
                    }
                    Text(notice.date, style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline)
                }
            }
            Icon(Icons.Filled.Download, null, tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp))
        }
    }
}

// ═══════════════════════════════════════════════════════════════
//  CAMPUS MAP SCREEN
// ═══════════════════════════════════════════════════════════════

data class CampusLocation(
    val name: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

val campusLocations = listOf(
    CampusLocation("Main Academic Block", "Central hub for classrooms and faculty offices", Icons.Filled.School),
    CampusLocation("Library", "50,000+ books, journals, e-resources, 24x7 digital access", Icons.Filled.LocalLibrary),
    CampusLocation("Cafeteria & Food Court", "Hygienic food, multiple cuisines, indoor & outdoor seating", Icons.Filled.Restaurant),
    CampusLocation("Sports Complex", "Basketball, badminton, cricket ground, indoor gym", Icons.Filled.SportsBasketball),
    CampusLocation("Auditorium", "Capacity 800+, equipped for events and seminars", Icons.Filled.TheaterComedy),
    CampusLocation("Boys Hostel", "24x7 Wi-Fi, mess, security, recreational facilities", Icons.Filled.Bed),
    CampusLocation("Girls Hostel", "Safe, secure, modern amenities for women students", Icons.Filled.Bed),
    CampusLocation("Medical Centre", "24-hour health services and first aid", Icons.Filled.LocalHospital),
    CampusLocation("Innovation Hub (IQUBE)", "Startup incubation, makerspaces, design labs", Icons.Filled.Lightbulb),
    CampusLocation("Placement Cell", "Career counselling, interview prep, industry connect", Icons.Filled.Work),
    CampusLocation("VU Wellness Centre", "Mental & physical wellbeing programs", Icons.Filled.HealthAndSafety),
    CampusLocation("Parking Area", "Dedicated parking for students, faculty & visitors", Icons.Filled.LocalParking),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusMapScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(VU_BlueDark, VU_Blue)))
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                    }
                    Column {
                        Text("Campus Map", style = MaterialTheme.typography.titleLarge,
                            color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Survey No. 2,3,4 Laxmi Nagar, Kondhwa Budruk, Pune",
                            style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.75f))
                    }
                }
                Spacer(Modifier.height(12.dp))
                // Static map placeholder
                Card(
                    onClick = {},
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.15f))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Filled.Map, null, tint = VU_Gold, modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Open in Google Maps", style = MaterialTheme.typography.bodyMedium,
                            color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text("Campus Facilities",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp))
            }
            items(campusLocations) { loc ->
                CampusLocationCard(loc)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusLocationCard(loc: CampusLocation) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(VU_Blue.copy(0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(loc.icon, null, tint = VU_Blue, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(loc.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(loc.description, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline, maxLines = 2)
            }
            Icon(Icons.Filled.Directions, null, tint = VU_Blue, modifier = Modifier.size(20.dp))
        }
    }
}
