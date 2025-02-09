package com.target.targetcasestudy

import android.net.Network
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DealListViewModel @Inject constructor(var repository: Repository) : ViewModel() {
    val dealList = MutableLiveData<List<Deal>>()
    val dealLiveData = MutableLiveData<Deal>()

    fun getDealList() {
        viewModelScope.launch {
            try {
                val deals = repository.retrieveDeals().deals
                dealList.postValue(deals)
            } catch (e: Exception) {

            }
        }
    }

    fun getDeal(id: String) {
        viewModelScope.launch {
            try {
                val deal = repository.retrieveDeals(id)
                dealLiveData.postValue(deal)
            } catch (except: Exception) {

            }
        }
    }
}