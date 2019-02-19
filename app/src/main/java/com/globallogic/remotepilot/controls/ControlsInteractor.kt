package com.globallogic.remotepilot.controls

import com.globallogic.remotepilot.data.ControlsModel
import com.globallogic.remotepilot.data.ControlsType

class ControlsInteractor : Contract.Interactor{

    private lateinit var controlsModel: ControlsModel

    constructor(){
        controlsModel = ControlsModel()
    }

    override fun transferSpeed(value: Number) = controlsModel.modifyControls(value.toInt(), ControlsType.SPEED)
    override fun transferLeftVal(value: Number) = controlsModel.modifyControls(value.toInt(), ControlsType.LEFT)
    override fun transferRightVal(value: Number) = controlsModel.modifyControls(value.toInt(), ControlsType.RIGHT)

}