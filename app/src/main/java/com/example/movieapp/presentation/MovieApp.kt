package com.example.movieapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.data.model.MenuItem
import com.example.movieapp.presentation.ui.theme.MovieAppTheme
import com.example.movieapp.presentation.util.Navigation
import com.example.movieapp.presentation.util.Screen
import kotlinx.coroutines.launch


@Composable
fun MovieApp() {

    MovieAppTheme {

        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        val items = arrayListOf(
           MenuItem(0,context.resources.getString(R.string.anasayfa),"", Icons.Outlined.Home),
            MenuItem(1,context.resources.getString(R.string.populerFilmler),"",Icons.Outlined.FavoriteBorder)
        )

        val selectedItem = remember { mutableStateOf(items[0]) }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()) {

                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null, modifier = Modifier.size(20.dp)) },
                            label = { Text(text = item.title, fontSize = 12.sp) },
                            shape = RoundedCornerShape(5.dp),
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                when(item.id){
                                    0->{
                                        navController.navigate(Screen.MainScreen.route){
                                            popUpTo(navController.graph.findStartDestination().id){
                                                inclusive=false
                                            }
                                        }

                                        selectedItem.value = items.find { it.id == 0 } ?: items[0]
                                    }
                                    1 ->{
                                        navController.navigate(Screen.PopularMoviesScreen.route){
                                            popUpTo(navController.graph.findStartDestination().id){
                                                inclusive=false
                                            }
                                        }

                                        selectedItem.value = items.find { it.id == 1 } ?: items[0]
                                    }


                                }

                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                        )
                    }

                }
            },
            drawerState = drawerState
            ,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = drawerState.isOpen
            /* !GlobalValues.MAP_PAGE_ACTIVE.value*/

        ){
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.padding(it)){

                    Navigation(navController = navController,drawerState)
                }
            }
        }




    }

}