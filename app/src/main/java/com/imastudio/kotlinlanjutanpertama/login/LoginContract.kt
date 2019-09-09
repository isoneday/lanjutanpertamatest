package com.imastudio.kotlinlanjutanpertama.login

import android.content.Context
import com.imastudio.kotlinlanjutanpertama.base.BaseView

interface LoginContract {

interface  Presenter{
    fun login(name:String,context: Context)
}

    interface view :BaseView{
        fun checkEmpty()
        fun pindahHalaman()
    }
}