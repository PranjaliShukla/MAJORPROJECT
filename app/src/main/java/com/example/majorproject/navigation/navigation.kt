package com.example.majorproject.navigation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.majorproject.ui.screens.*

@Composable
fun AppNavHost() {

    val nav = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = nav, startDestination = "splash") {

        // SPLASH
        composable("splash") {
            SplashScreen {
                nav.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }

        // HOME
        composable("home") {
            HomeScreen(
                onImageSelected = { uri ->
                    nav.navigate("processing/${Uri.encode(uri.toString())}")
                },
                onVideoSelected = { uri ->
                    nav.navigate("processing/${Uri.encode(uri.toString())}")
                }
            )
        }

        // PROCESSING
        composable(
            route = "processing/{uri}",
            arguments = listOf(
                navArgument("uri") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val uriString =
                backStackEntry.arguments?.getString("uri")

            val uri =
                uriString?.let { Uri.parse(Uri.decode(it)) }

            if (uri != null) {

                val inputStream =
                    context.contentResolver.openInputStream(uri)

                val original =
                    BitmapFactory.decodeStream(inputStream)

                inputStream?.close()

                val bitmap =
                    original?.let {
                        Bitmap.createScaledBitmap(it, 224, 224, true)
                    }

                if (bitmap != null) {
                    ProcessingScreen(
                        bitmap = bitmap,
                        onResult = { result, confidence ->
                            nav.navigate(
                                "result/$result/$confidence/${Uri.encode(uri.toString())}"
                            )
                        },
                        onBack = { nav.popBackStack() }
                    )
                }
            }
        }

        // RESULT
        composable(
            route = "result/{result}/{confidence}/{uri}",
            arguments = listOf(
                navArgument("result") { type = NavType.StringType },
                navArgument("confidence") { type = NavType.FloatType },
                navArgument("uri") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val result =
                backStackEntry.arguments?.getString("result") ?: "REAL"

            val confidence =
                backStackEntry.arguments?.getFloat("confidence") ?: 0f

            val uriString =
                backStackEntry.arguments?.getString("uri")

            val imageUri =
                uriString?.let { Uri.parse(Uri.decode(it)) }

            ResultScreen(
                result = result,
                confidence = confidence,
                imageUri = imageUri,
                onBack = { nav.popBackStack() },
                onAnalyzeAgain = {
                    nav.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}