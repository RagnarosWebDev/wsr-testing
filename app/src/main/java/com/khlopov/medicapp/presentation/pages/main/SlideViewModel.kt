package com.khlopov.medicapp.presentation.pages.main

import androidx.lifecycle.ViewModel
import com.khlopov.medicapp.R
import com.khlopov.medicapp.domain.model.Slide

class SlideViewModel : ViewModel() {
    private val slides = mutableListOf(
        Slide("asd", "asd", R.drawable.icon_list_1),
        Slide("asd1", "asd1", R.drawable.icon),
        Slide("asd1", "asd1", R.drawable.icon)
    )

    fun count(): Int {
        return slides.size
    }

    fun byIndex(index: Int): Slide {
        return slides[index];
    }
}