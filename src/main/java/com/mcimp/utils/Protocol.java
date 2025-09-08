package com.mcimp.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.Reader;

enum Status {
    Ok,
    Err;

    @Override
    public String toString() {
        switch (this) {
            case Ok:
                return "OK";
            case Err:
                return "ERR";
            default:
                throw new RuntimeException("unreachable");
        }
    }

    public static Status fromString(String status) {
        switch (status) {
            case "OK":
                return Status.Ok;
            case "ERR":
                return Status.Err;
            default:
                throw new RuntimeException("invalid action id");
        }
    }
}

enum Command {
    Upload,
    Download;

    public int toInt() {
        switch (this) {
            case Upload:
                return 1;
            case Download:
                return 2;
            default:
                throw new RuntimeException("unreachable when all branches are checked");
        }
    }

    public static Command fromInt(int c) {
        return switch (c) {
            case 1 -> Command.Download;
            case 2 -> Command.Upload;
            default -> throw new RuntimeException("invalid command int");
        };
    }
}

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

    public void writeCommand(int command) {

    }

    public void writeStatus(PrintWriter writer, Status status) {

    }

    public void writeHeader(DataOutputStream out, long length) {

    }
}
