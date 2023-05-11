package pe.edu.upc.appsuperzound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.appsuperzound.ui.screens.Albums
import pe.edu.upc.appsuperzound.ui.screens.HomeScreen
import pe.edu.upc.appsuperzound.ui.screens.MusicalAlbumViewModel
import pe.edu.upc.appsuperzound.ui.screens.AlbumsFavorite


import pe.edu.upc.appsuperzound.ui.theme.AppSuperZoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSuperZoundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Home" ){
                        composable("Home"){
                            HomeScreen(
                                onFindAlbums = { navController.navigate("Albums") },
                                onFavouriteAlbum = { navController.navigate("FavoriteProduct") }
                            ) {
                            }
                        }
                        composable("Albums"){
                            val context = applicationContext
                            val viewModel: MusicalAlbumViewModel by viewModels()
                            Albums(viewModel)
                        }

                        composable("FavoriteProduct"){
                            val context = applicationContext
                            val viewModel: MusicalAlbumViewModel by viewModels()
                            AlbumsFavorite(viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppSuperZoundTheme {
        Greeting("Android")
    }
}