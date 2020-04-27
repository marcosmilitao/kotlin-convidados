package com.militao.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.militao.convidados.service.model.GuestModel
import com.militao.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContext)

    private val mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private val mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }

        if (id == 0) {
            mSaveGuest.value = mGuestRepository.save(guest)
        } else {
            mSaveGuest.value = mGuestRepository.update(guest)
        }

    }

    fun load(id: Int) {

        mGuest.value = mGuestRepository.get(id)
    }
}