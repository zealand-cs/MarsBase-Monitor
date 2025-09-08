package com.mcimp.utils;

public enum Status {
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

