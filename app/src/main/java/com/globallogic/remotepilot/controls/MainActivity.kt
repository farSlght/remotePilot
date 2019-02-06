package com.globallogic.remotepilot.controls

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.globallogic.remotepilot.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sb_vertical.view.*

class MainActivity : AppCompatActivity(), Contract.View, SeekBar.OnSeekBarChangeListener {

    private val mThrottleValue = PublishSubject.create<Int>()
    private var mLeftValue = PublishSubject.create<Int>()
    private var mRightValue = PublishSubject.create<Int>()

    lateinit var presenter: Contract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ControlsPresenter()
        speed.throttle.setOnSeekBarChangeListener(this)
        leftSteering.throttle.setOnSeekBarChangeListener(this)
        rightSteering.throttle.setOnSeekBarChangeListener(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onProgressChanged(seekBar: SeekBar?, value: Int, p2: Boolean) {
        when (seekBar?.rootView?.id) {
            R.id.speed -> {
                mThrottleValue.onNext(value)
                tv_speedVal.text = "Value: $value"
            }
            R.id.leftSteering -> {
                mLeftValue.onNext(value)
                tv_leftVal.text = "Value: $value"
            }
            R.id.rightSteering -> {
                mRightValue.onNext(value)
                tv_rightVal.text = "Value: $value"
            }
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun <T> speedThrottleChanged(eventType: Class<T>): Observable<T>? = mThrottleValue.ofType(eventType)
    override fun <T> leftThrottleChanged(eventType: Class<T>): Observable<T>?  = mLeftValue.ofType(eventType)
    override fun <T> rightThrottleChanged(eventType: Class<T>): Observable<T>?  = mRightValue.ofType(eventType)
}


