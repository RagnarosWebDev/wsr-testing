package com.khlopov.medicapp.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khlopov.medicapp.domain.model.AppStoreInfo
import com.khlopov.medicapp.domain.repository.AppStoreInfoRepository

class AppStoreInfoViewModel(private val userInfo: AppStoreInfoRepository): ViewModel() {

    private val _info: MutableLiveData<AppStoreInfo> = MutableLiveData()

    val info: LiveData<AppStoreInfo> get() = _info

    suspend fun loadData() {
        userInfo.loadInfo().collect {
            _info.value = it
        }
    }

    suspend fun setSlidesViewed() {
        _info.value!!.isView = true
        _info.postValue(info.value)
        userInfo.saveInfo(_info.value!!)
    }
}