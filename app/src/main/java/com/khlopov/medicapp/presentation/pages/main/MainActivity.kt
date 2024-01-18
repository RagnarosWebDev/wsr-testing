package com.khlopov.medicapp.presentation.pages.main

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.khlopov.medicapp.presentation.pages.login.LoginActivity
import com.khlopov.medicapp.presentation.AppStoreInfoViewModel
import com.khlopov.medicapp.ui.theme.MedicAppTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val mainModule = module {
    single { SlideViewModel() }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(mainModule)
        setContent {
            MedicAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main(mainViewModel: AppStoreInfoViewModel = koinInject()) {
    val coroutineScope = rememberCoroutineScope()
    val info = mainViewModel.info.observeAsState()
    val mContext = LocalContext.current


    LaunchedEffect(true, block = {
        coroutineScope.launch {
            mainViewModel.loadData();
        }
    })


    if (info.value == null) {
        Box {}
        return
    }

    if (info.value!!.isView) {
        mContext.startActivity(Intent(mContext, LoginActivity::class.java).apply { flags =
            FLAG_ACTIVITY_CLEAR_TASK })
        return
    }


    SlideListView()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MedicAppTheme {
        Main()
    }
}