package com.mcimp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public class SensorPacket {
    public SensorPacketType type;
    public String name;
    public String value;

    public SensorPacket(String name, String value, SensorPacketType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public void writePacket(Writer writer) throws IOException {
        writer.write(type.toInt());
        writer.write(name + "\n");
        writer.write(value + "\n");
    }

    public static SensorPacket readPacket(BufferedReader reader) throws IOException {
        var type = SensorPacketType.fromInt(reader.read());
        var name = reader.readLine();
        var value = reader.readLine();

        SensorPacket packet = new SensorPacket(name, value, type);
        return packet;
    }
}