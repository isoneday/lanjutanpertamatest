package com.imastudio.kotlinlanjutanpertama.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.kotlinlanjutanpertama.MainActivity
import com.imastudio.kotlinlanjutanpertama.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(),LoginContract.view {


    lateinit var presenter :LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        initClickListener()
    }

    private fun initClickListener() {
        btn_submit.onClick {
            presenter.login(edt_nama.text.toString().trim(),this@LoginActivity)
        }
    }

    private fun initPresenter() {
        presenter = LoginPresenter()
    }

    override fun checkEmpty() {
   toast("tidak boleh kosong")
    }

    override fun pindahHalaman() {
        startActivity<MainActivity>()
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
