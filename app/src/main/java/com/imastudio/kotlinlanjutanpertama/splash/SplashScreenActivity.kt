package com.imastudio.kotlinlanjutanpertama.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.kotlinlanjutanpertama.R
import com.imastudio.kotlinlanjutanpertama.login.LoginActivity
import com.imastudio.kotlinlanjutanpertama.session.SessionManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashScreenActivity : AppCompatActivity(),SplashContract.view {

    lateinit var sessionManager: SessionManager
    var name :String? =null

    lateinit var presenter : SplashScreenPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initPresenter()
        presenter.delaySplash(2000)
    }

    private fun initPresenter() {
        presenter = SplashScreenPresenter()
    }

    override fun pindaHalaman() {
        startActivity<LoginActivity>()
        finish()

    }

    override fun welcomeMsg() {
        sessionManager = SessionManager(this)
        name =sessionManager.nama
        if (name!=null) toast("selamat datang kembali: $name")
        else toast("hi,selamat datang")
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}
