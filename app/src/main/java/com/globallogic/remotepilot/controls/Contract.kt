package com.globallogic.remotepilot.controls

import com.globallogic.remotepilot.base.BasePresenter
import com.globallogic.remotepilot.base.BaseView
import io.reactivex.Observable

interface Contract{

    interface View : BaseView{
    fun <T> speedThrottleChanged(eventType: Class<T>): Observable<T>?
    fun <T> leftThrottleChanged(eventType: Class<T>): Observable<T>?
    fun <T> rightThrottleChanged(eventType: Class<T>): Observable<T>?
    }

    interface Presenter : BasePresenter<View>{
        fun transferSpeedValue(value: Number)
        fun transferLeftValue(value: Number)
        fun transferRightValue(value: Number)
    }

    interface Interactor {
        fun transferSpeed(value: Number)
    }
}
