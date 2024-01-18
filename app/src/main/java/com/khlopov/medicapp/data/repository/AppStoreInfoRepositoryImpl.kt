package com.khlopov.medicapp.data.repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.khlopov.medicapp.domain.model.AppStoreInfo
import com.khlopov.medicapp.domain.repository.AppStoreInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppStoreInfoRepositoryImpl(context: Context) : AppStoreInfoRepository {
    companion object {
        val KEY_IS_VIEW = preferencesKey<Boolean>("isView")
        val KEY_TOKEN = preferencesKey<String>("token")

    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "AppInfo")


    override suspend fun loadInfo(): Flow<AppStoreInfo> {
        return dataStore.data.map { pref ->
            AppStoreInfo(
                pref[KEY_IS_VIEW] ?: false, "",
                pref[KEY_TOKEN] ?: ""
            )
        }
    }

    override suspend fun saveInfo(appStoreInfo: AppStoreInfo) {
        dataStore.edit { pref ->
            pref[KEY_IS_VIEW] = appStoreInfo.isView
            pref[KEY_TOKEN] = appStoreInfo.token
        }
    }
}