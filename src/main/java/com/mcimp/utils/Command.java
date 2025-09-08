package com.mcimp.utils;

/// Not in scope of this project
public enum Command {

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
