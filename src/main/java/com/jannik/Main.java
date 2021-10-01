package com.jannik;

import com.jannik.frontend.ConsoleHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Integer size = Integer.parseInt(args[1]);
        String fileName = args[0];

        final var consoleHandler = new ConsoleHandler(size, fileName);

        consoleHandler.interact();
    }
}
