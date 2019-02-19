package com.globallogic.remotepilot.controls

class ControlsPresenter: Contract.Presenter{

    lateinit var view: Contract.View
    lateinit var controlsInteractor: ControlsInteractor

    constructor(){
        controlsInteractor = ControlsInteractor()
    }

    override fun attachView(view: Contract.View) {
        this.view = view

        view.speedThrottleChanged(Integer::class.java)?.subscribe {
            it -> transferSpeedValue(it)
        }
        view.leftThrottleChanged(Integer::class.java)?.subscribe {
            it -> transferLeftValue(it)
        }
        view.rightThrottleChanged(Integer::class.java)?.subscribe {
            it -> transferRightValue(it)
        }
    }

    override fun detachView() {
        // cancel any network activity
    }

    override fun transferSpeedValue(value: Number) = controlsInteractor.transferSpeed(value)

    override fun transferLeftValue(value: Number) = controlsInteractor.transferLeftVal(value)

    override fun transferRightValue(value: Number) = controlsInteractor.transferRightVal(value)
}
