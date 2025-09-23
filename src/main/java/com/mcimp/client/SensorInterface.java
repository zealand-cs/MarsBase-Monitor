package com.mcimp.client;

public interface SensorInterface extends Runnable {

    String getType();
    double generateValue();
}
