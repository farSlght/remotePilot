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
    }

    override fun detachView() {
        // cancel any network activity
    }

    override fun transferSpeedValue(value: Number) = controlsInteractor.transferSpeed(value)


    override fun transferLeftValue(value: Number) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transferRightValue(value: Number) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
