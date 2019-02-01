package com.globallogic.remotepilot.data;

import android.util.Log;

public class ControlsModel {

    private int throttleValue;
    private int leftEngine;
    private int rightEngine;
    private int controlsDataBitSet;

    public ControlsModel() {
        controlsDataBitSet = 0;
    }

    public boolean transferControls() {

        //TODO generate audio and transfer via MiniJack
        return false;
    }

    public void modifySpeed(int throttleValue){
        controlsDataBitSet = controlsDataBitSet ^ throttleValue;
        Log.d("Modified controls: ", Integer.toBinaryString(throttleValue));
    }

    public int getThrottleValue() {
        return throttleValue;
    }

    public void setThrottleValue(int throttleValue) {
        this.throttleValue = throttleValue;
    }

    public int getLeftEngine() {
        return leftEngine;
    }

    public void setLeftEngine(int leftEngine) {
        this.leftEngine = leftEngine;
    }

    public int getRightEngine() {
        return rightEngine;
    }

    public void setRightEngine(int rightEngine) {
        this.rightEngine = rightEngine;
    }
}
