package com.khlopov.medicapp.presentation.pages.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.khlopov.medicapp.R
import com.khlopov.medicapp.presentation.components.Counter
import com.khlopov.medicapp.presentation.components.enterCode.EnterCode
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun CodePage(navController: NavController, vm: LoginPageViewModel = koinInject()) {
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current;


    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp)
                .width(30.dp)
                .height(30.dp)
                .background(Color(0xFFF5F5F9), RoundedCornerShape(5.dp)).clickable {
                    navController.navigateUp()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .align(Center)
        ) {
            Text(
                "Введите код из E-mail",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))


            Box(modifier = Modifier.align(CenterHorizontally)) {
                EnterCode(4) { code ->
                    coroutine.launch {
                        vm.login(code)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))


            Counter {
                coroutine.launch {
                    vm.sendCode()
                    Toast.makeText(context, "Код переотправлен", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}