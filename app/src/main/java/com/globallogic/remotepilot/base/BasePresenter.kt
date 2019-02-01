package com.globallogic.remotepilot.base

interface BasePresenter<A>{

    fun attachView(view: A)
    fun detachView()
}
