package com.imastudio.kotlinlanjutanpertama.splash

import com.imastudio.kotlinlanjutanpertama.base.BaseView

interface SplashContract {

    interface  Presenter{
        fun delaySplash(timer: Long)
    }

    interface view: BaseView{
        fun pindaHalaman()
        fun welcomeMsg()
    }
}