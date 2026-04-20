package com.example.collegeandroidapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        label = "Home",
        icon = Icons.Filled.Home
    )
    object Courses : BottomNavItem(
        route = "courses",
        label = "Courses",
        icon = Icons.Filled.MenuBook
    )
    object Events : BottomNavItem(
        route = "events",
        label = "Events",
        icon = Icons.Filled.Event
    )
    object More : BottomNavItem(
        route = "more",
        label = "More",
        icon = Icons.Filled.GridView
    )
}
