package com.globallogic.remotepilot.data;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import java.nio.ByteBuffer;

public class ControlsModel {
    private final String TAG = getClass().getSimpleName();

    private int throttleValue;
    private int leftEngine;
    private int rightEngine;
    private int controlsDataBitSet;

    public ControlsModel() {
        controlsDataBitSet = 0;
    }

    public boolean transferControls() {

        byte bytes[] = ByteBuffer.allocate(4).putInt(controlsDataBitSet).array();
        int bufsize = AudioTrack.getMinBufferSize(8000,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        AudioTrack trackplayer = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufsize,
                AudioTrack.MODE_STREAM);
        trackplayer.play();     /// TODO: check out "java.lang.IllegalStateException: play() called on uninitialized AudioTrack." on high values of throttles
        trackplayer.write(bytes, 0, bytes.length);
        for (byte b : bytes) {
            Log.d(TAG, String.valueOf(Integer.toBinaryString(b & 0xFF)));
        }
        return false;
    }

    public void modifyControls(int value, ControlsType type) {

        switch (type) {
            case SPEED: throttleValue = value;
            break;
            case LEFT: leftEngine = value;
            break;
            case RIGHT: rightEngine = value;
            break;
        }
        controlsDataBitSet = 0;
        controlsDataBitSet = throttleValue | leftEngine | rightEngine;
        transferControls();
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
