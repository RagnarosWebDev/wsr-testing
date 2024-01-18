package com.khlopov.medicapp.presentation.pages.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.khlopov.medicapp.R
import com.khlopov.medicapp.presentation.components.CustomTextField
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun LoginPage(navController: NavController, vm: LoginPageViewModel = koinInject()) {
    val coroutine = rememberCoroutineScope()
    val email = vm.email.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Row(
            modifier = Modifier

                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hi_emoji),
                contentDescription = "",
                modifier = Modifier.width(30.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Добро пожаловать!",
                style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.align(CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Войдите, чтобы пользоваться функциями\r\nприложения",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(45.dp))
        Text(text = "Вход по E-mail")
        Spacer(modifier = Modifier.height(5.dp))
        CustomTextField(text = email.value!!, onValueChangeListener = {
            vm.changeEmail(it)
        })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutine.launch {
                    val res = vm.sendCode();
                    if (!res.first) {
                        Toast.makeText(context, res.second, Toast.LENGTH_LONG).show()
                        return@launch
                    }

                    navController.navigate("/code")
                }
            }, modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0XFF1A6FEE),
                contentColor = Color.White,
                disabledContainerColor = Color(0XFFC9D4FB),
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = vm.isEmailValid
        ) {
            Text(
                text = "Далее",
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = "Или войдите с помощью",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 15.sp,
                color = Color(0XFF939396)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {}, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
            border = BorderStroke(1.dp, Color(0XFFEBEBEB)),
        ) {
            Text(
                text = "Войти с Яндекс", style = TextStyle(
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

