package com.mcimp.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.Reader;

public class Protocol {
    public int readCommand(Reader r) {
        return 0;
    }

    public Status readStatus(Reader r) {
        return Status.Ok;
    }

    public String readHeader(DataInputStream in) {
        return "";
    }

    public void writeCommand(Command command) {

    }

    public void writeStatus(PrintWriter writer, Status status) {

    }

    public void writeHeader(DataOutputStream out, long length) {

    }
}
