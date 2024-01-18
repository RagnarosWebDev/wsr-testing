package com.khlopov.medicapp.presentation.components.enterCode

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterCode(count: Int = 4, onSuccess: (code: String) -> Unit) {
    val code = remember {
        (1..count).toList().map { mutableStateOf("") }
    }
    val focuses = remember { (1..count).toList().map { FocusRequester() } }

    Row(
        modifier = Modifier.width(60.dp * count),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(count) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFF5F5F9), RoundedCornerShape(5.dp))
                    .border(1.dp, Color(0xFFEBEBEB), RoundedCornerShape(5.dp))
                    .width(40.dp)
                    .height(40.dp),
                contentAlignment = Center
            ) {
                BasicTextField(
                    value = code[it].value, { text ->
                        if (text.isEmpty()) {
                            code[it].value = text

                            if (it != 0) {
                                focuses[it].freeFocus();
                                focuses[it - 1].requestFocus();
                            }

                        } else {
                            code[it].value = text[text.length - 1].toString()

                            if (it != count - 1) {
                                focuses[it + 1].requestFocus();
                                focuses[it].freeFocus();
                            } else {
                                onSuccess(code.map { it.value }.joinToString { "" })
                            }
                        }
                    }, modifier = Modifier
                        .align(Center)
                        .focusRequester(focuses[it]),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }

}