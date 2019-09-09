package com.imastudio.kotlinlanjutanpertama.splash


import android.os.Handler
import com.imastudio.kotlinlanjutanpertama.base.BasePresenter

class SplashScreenPresenter(var splashView: SplashContract.view? = null) : BasePresenter<SplashContract.view>,
    SplashContract.Presenter {

    override fun delaySplash(timer: Long) {
        Handler().postDelayed(Runnable {
            splashView?.pindaHalaman()
            splashView?.welcomeMsg()
        }, timer)
    }

    override fun onAttach(view: SplashContract.view) {
        splashView = view
    }

    override fun onDettach() {
        splashView = null
    }
}