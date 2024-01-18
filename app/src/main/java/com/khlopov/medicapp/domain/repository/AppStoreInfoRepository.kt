package com.khlopov.medicapp.domain.repository

import com.khlopov.medicapp.domain.model.AppStoreInfo
import kotlinx.coroutines.flow.Flow

interface AppStoreInfoRepository {
    suspend fun loadInfo(): Flow<AppStoreInfo>
    suspend fun saveInfo(appStoreInfo: AppStoreInfo)
}