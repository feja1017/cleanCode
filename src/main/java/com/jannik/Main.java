package com.jannik;

import com.jannik.frontend.ConsoleHandler;

import java.io.IOException;

public class Main {

    private static final int DEFAULT_PAGE_SIZE = 5;

    public static void main(String[] args) throws IOException {
        String fileName = parseFileName(args);
        Integer size = parsePageSize(args);

        final var consoleHandler = new ConsoleHandler(size, fileName);
        consoleHandler.interact();
    }

    private static Integer parsePageSize(String[] args) {
        if (args.length > 1) {
            try {
                return Integer.parseInt(args[1]);
            } catch (Exception e) {
                // use logger... But here console output = table output
            }
        }
        return DEFAULT_PAGE_SIZE;
    }

    private static String parseFileName(String[] args) {
        return args[0];
    }
}
