package com.khlopov.medicapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun Counter(resendCode: () -> Unit) {
    val counter = remember {
        mutableIntStateOf(60)
    }
    
    LaunchedEffect(key1 = counter.intValue, block = {
        delay(1000)

        counter.intValue--

        if(counter.intValue == -1) {
            counter.intValue = 60;
            resendCode();
        }
    })


    Text(
        text = "Отправить код повторно можно будет через ${counter.intValue} секунд", style = TextStyle(
            fontSize = 13.sp,
            color = Color(0xFF939396)
        ),
        textAlign = TextAlign.Center
    )
}