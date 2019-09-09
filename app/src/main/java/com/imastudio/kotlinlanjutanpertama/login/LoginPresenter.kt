package com.imastudio.kotlinlanjutanpertama.login

import android.content.Context
import com.imastudio.kotlinlanjutanpertama.base.BasePresenter
import com.imastudio.kotlinlanjutanpertama.session.SessionManager

class LoginPresenter (var loginview :LoginContract.view?=null)
    :BasePresenter<LoginContract.view>,LoginContract.Presenter {


    override fun onAttach(view: LoginContract.view) {
   loginview = view
    }

    override fun onDettach() {
    loginview =null
    }

    override fun login(name: String, context: Context) {
    if (name.isEmpty()){
        loginview?.checkEmpty()
    }else{
        val session = SessionManager(context)
        session.nama = name
        loginview?.pindahHalaman()
    }

    }
}