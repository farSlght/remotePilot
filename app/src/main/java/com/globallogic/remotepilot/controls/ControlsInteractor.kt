package com.globallogic.remotepilot.controls

import com.globallogic.remotepilot.data.ControlsModel

class ControlsInteractor : Contract.Interactor{

    private lateinit var controlsModel: ControlsModel

    constructor(){
        controlsModel = ControlsModel()
    }

    override fun transferSpeed(value: Number) = controlsModel.modifySpeed(value.toInt())

}