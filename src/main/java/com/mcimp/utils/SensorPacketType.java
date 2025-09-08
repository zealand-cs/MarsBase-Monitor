package com.mcimp.utils;

public enum SensorPacketType {
    Temperature,
    Co2,
    Oxygen;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public int toInt() {
        return switch (this) {
            case Temperature -> 1;
            case Co2 -> 2;
            case Oxygen -> 3;
        };
    }

    public static SensorPacketType fromInt(int value) {
        return switch (value) {
            case 1 -> Temperature;
            case 2 -> Co2;
            case 3 -> Oxygen;
            default -> throw new IllegalArgumentException("Invalid sensor packet type");
        };
    }
}
