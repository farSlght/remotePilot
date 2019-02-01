package com.globallogic.remotepilot.controls

class ControlsPresenter: Contract.Presenter{

    lateinit var view: Contract.View
    lateinit var interactor: Interactor

    constructor(){
        interactor = Interactor()
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

    override fun transferSpeedValue(value: Number) = interactor.transferSpeed(value)


    override fun transferLeftValue(value: Number) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transferRightValue(value: Number) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
