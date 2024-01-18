package com.khlopov.medicapp.presentation.pages.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.khlopov.medicapp.presentation.AppStoreInfoViewModel
import com.khlopov.medicapp.presentation.components.slide.SlideView
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideListView(vm: SlideViewModel = koinInject(), main: AppStoreInfoViewModel = koinInject()) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    HorizontalPager(state = pagerState, pageCount = vm.count()) { page ->
        SlideView(
            vm.byIndex(page),
            page,
            count = vm.count(),
            onSkipClick = {
                coroutineScope.launch {
                    if (it + 1 != vm.count()) {
                        pagerState.scrollToPage(it + 1)
                        return@launch
                    }

                    main.setSlidesViewed();
                    navController.navigate("/login")
                }
            }
        )
    }
}