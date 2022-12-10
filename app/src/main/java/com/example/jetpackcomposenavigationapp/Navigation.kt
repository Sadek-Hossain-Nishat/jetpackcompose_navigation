package com.example.jetpackcomposenavigationapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

const val NO_ARGUMENT="no_argument"
@Composable
fun Navigation() {
    val navController = rememberNavController( )
    NavHost(navController = navController, startDestination = Screen.MainScreen.route)
    {
        composable(route = Screen.MainScreen.route) {

            MainScreen(navController = navController)

        }
        composable(
            route = Screen.DetailScreen.route+"/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Sadek"
                    nullable = true

                }
            )
        ) { entry->

            DetailScreen(name = entry.arguments?.getString("name"))

        }
    }


    
}


@Composable
fun MainScreen(navController: NavController) {

    var text by remember{
        mutableStateOf("")

    }


    Column(verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 50.dp)) {

        TextField(value = text, onValueChange =
        {
            text = it
        },
        modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {

          if (text.trim().isNotEmpty())       navController.navigate(Screen.DetailScreen.withArgs(text))
            else navController.navigate(Screen.DetailScreen.withArgs(NO_ARGUMENT))



        },
        modifier = Modifier.align(Alignment.End))
        {

            Text(text = "To DetailScreen")



        }

    }


}

@Composable
fun DetailScreen(
    name:String?
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!name.equals(NO_ARGUMENT))
        Text(text = "Hello $name")
        else Text(text = "Hello")


    }

}


