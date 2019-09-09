package com.imastudio.kotlinlanjutanpertama.base

interface BasePresenter <T : BaseView>{
    fun onAttach(view:T)
    fun onDettach()
}