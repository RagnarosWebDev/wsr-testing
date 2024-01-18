package com.khlopov.medicapp.presentation.pages.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khlopov.medicapp.data.repository.UserRepositoryImpl
import com.khlopov.medicapp.domain.repository.UserRepository
import com.khlopov.medicapp.ui.theme.MedicAppTheme
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val loginModule = module {
    single { LoginPageViewModel( get() ) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(loginModule)
        setContent {
            MedicAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    val navController = rememberNavController();

    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            LoginPage(navController)
        }

        composable("/code") {
            CodePage(navController)
        }
    }
}