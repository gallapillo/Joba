package com.gallapillo.joba.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.presentation.theme.BorderColor
import com.gallapillo.joba.presentation.theme.HoverBackground

enum class BottomNavigationItem(val icon: ImageVector, val route: Screen) {
    HOME(Icons.Default.Home, Screen.MainScreen),
    SEARCH(Icons.Default.Search, Screen.SearchScreen),
    RESPONSES(Icons.Default.Notifications, Screen.ResponsesScreen),
    PROFILE(Icons.Default.Person, Screen.ProfileScreen)

}

@Composable
fun BottomNavigationMenu (
    selectedItem : BottomNavigationItem,
    navController: NavController
) {
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        for (item in BottomNavigationItem.values()) {
            Image (
                imageVector = item.icon,
                contentDescription = "Item",
                modifier = Modifier.size(40.dp)
                    .weight(1f)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(item.route.route)
                    },
                colorFilter = if (item == selectedItem) ColorFilter.tint(HoverBackground) else ColorFilter.tint(
                    BorderColor)
            )
        }
    }
}