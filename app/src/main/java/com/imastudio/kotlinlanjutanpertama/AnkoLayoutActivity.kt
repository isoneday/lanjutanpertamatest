package com.imastudio.kotlinlanjutanpertama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AnkoLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {

                button{
                    text = "galery"
                    textSize = 13f

                    onClick {
                        toast("tampil toast")
                    }


                }.lparams(width= matchParent,height = wrapContent)
                button("wisata"){
                    toast("tampil toast")
                }.lparams(width= matchParent,height = wrapContent)
                button("luas segitiga"){

                    toast("tampil toast")
                }.lparams{
                    marginTop.and(22)

                }


        }
    }
}
