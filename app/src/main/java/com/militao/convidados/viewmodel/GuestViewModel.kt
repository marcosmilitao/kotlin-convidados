package com.militao.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.militao.convidados.service.constants.GuestConstants
import com.militao.convidados.service.model.GuestModel
import com.militao.convidados.service.repository.GuestRepository
import com.militao.convidados.view.GuestFormActivity

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {

        if (filter == GuestConstants.FILTER.EMPTY) {
            mGuestList.value = mGuestRepository.getAll()
        }else if (filter == GuestConstants.FILTER.ABSENT) {
            mGuestList.value = mGuestRepository.getAbsent()
        }else {
            mGuestList.value = mGuestRepository.getPresent()
        }
    }

    fun delete(id: Int) {
        mGuestRepository.delete(id)
    }
}