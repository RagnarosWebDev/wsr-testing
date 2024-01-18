package com.khlopov.medicapp.presentation.components.slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khlopov.medicapp.R
import com.khlopov.medicapp.domain.model.Slide


@Composable
fun SlideView(slide: Slide, position: Int, count: Int, onSkipClick: (position: Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Text(
                text = if (position + 1 != count) "Пропустить" else "Завершить",
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 20.dp)
                    .clickable {
                        onSkipClick(position)
                    },
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color(0xFF57A9FF)
                )
            )
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = slide.title, style = TextStyle(
                fontSize = 20.sp,
                color = Color(0xFF00B712),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(29.dp))


        Text(
            text = slide.description, style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF939396),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(60.dp))


        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(0.3F),
            Arrangement.SpaceBetween
        ) {
            repeat(count) { value ->
                Circle(isSelected = value == position)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = slide.image),
                contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .align(Center),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
fun Circle(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .background(
                if (isSelected) Color(0xFF57A9FF) else Color.White,
                RoundedCornerShape(30.dp)
            )
            .border(1.dp, Color(0xFF57A9FF), RoundedCornerShape(30.dp))
    )
}