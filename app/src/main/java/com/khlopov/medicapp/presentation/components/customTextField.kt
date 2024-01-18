package com.khlopov.medicapp.presentation.components

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomTextField(text: String, onValueChangeListener: (text: String) -> Unit) {
    BasicTextField(
        value = text,
        onValueChange = onValueChangeListener,
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(Color(0xFFF5F5F9))
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                innerTextField()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEBEBEB), RoundedCornerShape(10.dp)),
    )
}