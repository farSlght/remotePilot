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

        //TODO generate audio and transfer via MiniJack
        byte bytes[] = ByteBuffer.allocate(4).putInt(controlsDataBitSet).array();
        int bufsize = AudioTrack.getMinBufferSize(8000,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        AudioTrack trackplayer = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufsize,
                AudioTrack.MODE_STREAM);
        trackplayer.play();
        trackplayer.write(bytes, 0, bytes.length);
        return false;
    }

    public byte[] intToByteArray() {

        Log.d(TAG, "intToByteArray: ");

//        Log.d(TAG+"binaryString", String.valueOf(Byte.decode(binary)));

        byte bytes[] = ByteBuffer.allocate(4).putInt(controlsDataBitSet).array();
        for (byte b : bytes) {
            Log.d(TAG, String.valueOf(Integer.toBinaryString(b & 0xFF)));
        }

//  region manual type convertion
//        String binary = Integer.toBinaryString(controlsDataBitSet);
//        Log.d(TAG + "Modified controls: ", binary);
//
//        Log.d(TAG+"1", String.valueOf((byte) controlsDataBitSet >>> 24));
//        Log.d(TAG+"2", String.valueOf((byte) controlsDataBitSet >>> 16));
//        Log.d(TAG+"3", String.valueOf((byte) controlsDataBitSet >>> 8));
//        Log.d(TAG+"4", String.valueOf((byte) controlsDataBitSet));
//        Log.d(TAG, "end of intToByteArray\n\n");
//
//        return new byte[] {
//                (byte)(controlsDataBitSet >>> 24),
//                (byte)(controlsDataBitSet >>> 16),
//                (byte)(controlsDataBitSet >>> 8),
//                (byte)controlsDataBitSet
//        };
//  endregion
        return bytes;
    }

    public void modifySpeed(int throttleValue){
        controlsDataBitSet = controlsDataBitSet | throttleValue;
        Log.d(TAG+"New speed: ", Integer.toBinaryString(throttleValue));
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
