package com.globallogic.remotepilot.controls

import com.globallogic.remotepilot.data.ControlsModel

class Interactor : Contract.Interactor{

    private lateinit var controlsModel: ControlsModel

    constructor(){
        controlsModel = ControlsModel()
    }

    override fun transferSpeed(value: Number) = controlsModel.modifySpeed(value.toInt())

}